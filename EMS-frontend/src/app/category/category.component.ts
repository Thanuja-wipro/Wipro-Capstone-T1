import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';

interface Category {
  categoryID: number;
  name: string;
  description: string;
}

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categoryForm: FormGroup;
  editingCategory: Category | null = null;
  editingCategoryName: string = '';
  editingCategoryDescription: string = '';
  categories: Category[] = [];

  constructor(private fb: FormBuilder, private categoryService: CategoryService) {
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(categories => {
      console.log('Fetched categories:', categories);
      this.categories = categories;
    });
    
  }

  onSubmit() {
    if (this.editingCategory) {
      return;
    }

    const newCategory: Category = {
      categoryID: 0, 
      ...this.categoryForm.value
    };

    this.categoryService.createCategory(newCategory).subscribe(category => {
      this.categories.push(category);
      this.categoryForm.reset();
    });
  }

  editCategory(category: Category): void {
    this.editingCategory = { ...category };
    this.editingCategoryName = category.name;
    this.editingCategoryDescription = category.description;
  }
  
  updateCategory(): void {
    if (this.editingCategory) {
      this.editingCategory.name = this.editingCategoryName;
      this.editingCategory.description = this.editingCategoryDescription;

      this.categoryService.updateCategory(this.editingCategory).subscribe(updatedCategory => {
        const index = this.categories.findIndex(c => c.categoryID === updatedCategory.categoryID);
        if (index !== -1) {
          this.categories[index] = updatedCategory;
        }
        this.cancelEdit();
      });
    }
  }

  cancelEdit(): void {
    this.editingCategory = null;
    this.editingCategoryName = '';
    this.editingCategoryDescription = '';
  }

  deleteCategory(categoryID: number): void {
    this.categoryService.deleteCategory(categoryID).subscribe(() => {
      this.categories = this.categories.filter(c => c.categoryID !== categoryID);
    });
  }

  isEditing(): boolean {
    return this.editingCategory !== null;
  }
}
