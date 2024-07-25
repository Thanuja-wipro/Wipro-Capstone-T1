import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ExpenseService } from '../expense.service';

interface Expense {
  expenseID: number;
  userID: string;
  amount: string;
  date: string;
  categoryID: string;
  description: string;
}

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent implements OnInit {
  expenseForm: FormGroup;
  editingElementID: number | null = null;
  dataSource: Expense[] = [];
  userDropdownOptions: any[] = [];
  categoryDropdownOptions: any[] = [];

  constructor(private fb: FormBuilder, private expenseService: ExpenseService) {
    this.expenseForm = this.fb.group({
      userID: [''],
      amount: [''],
      date: [''],
      categoryID: [''],
      description: ['']
    });
  }

  ngOnInit(): void {
    this.loadUserDropdownOptions();
    this.loadCategoryDropdownOptions();
    this.loadTableData();
  }

  loadUserDropdownOptions(): void {
    this.expenseService.getUserDropdownOptions().subscribe(data => {
      this.userDropdownOptions = data;
    });
  }

  getUserName(userID: string): string {
    const user = this.userDropdownOptions.find(option => option.userID === userID);
    return user ? user.name : 'Unknown';
  }

  loadCategoryDropdownOptions(): void {
    this.expenseService.getCategoryDropdownOptions().subscribe(data => {
      this.categoryDropdownOptions = data;
    });
  }

  getCategoryName(categoryID: string): string {
    const category = this.categoryDropdownOptions.find(option => option.categoryID === categoryID);
    return category ? category.name : 'Unknown';
  }

  formatDate(date: string | null): string {
    if (!date) {
      return '--'; 
    }
    const d = new Date(date);  
    const year = d.getFullYear();
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  loadTableData(): void {
    this.expenseService.getExpenses().subscribe(data => {
      this.dataSource = data;
    });
  }

  onSubmit(): void {
    const formValues = this.expenseForm.value;
    const newExpense: any = {
      user: parseInt(formValues.userID),  
      amount: parseFloat(formValues.amount),  
      date: formValues.date,     
      category: parseInt(formValues.categoryID),  
      description: formValues.description
    };

    this.expenseService.createExpense(newExpense).subscribe(expense => {
      console.log('New expense created:', expense);
      this.loadTableData();
      this.expenseForm.reset();
    }, error => {
      console.error('Error creating expense:', error);
    });
  }

  editElement(element: Expense): void {
    this.editingElementID = element.expenseID;
  }

  updateElement(): void {
    if (this.editingElementID !== null) {
      const updatedExpense = this.dataSource.find(e => e.expenseID === this.editingElementID);
      console.log(updatedExpense);
      if (updatedExpense) {
        const finalUpdatedExpense: any = {
          expenseID: updatedExpense.expenseID,
          user: updatedExpense.userID,  
          amount: parseFloat(updatedExpense.amount),  
          date: updatedExpense.date,     
          category: updatedExpense.categoryID,  
          description: updatedExpense.description
        }
        console.log(finalUpdatedExpense);
        if (finalUpdatedExpense) {
          this.expenseService.updateExpense(finalUpdatedExpense).subscribe(expense => {
            const index = this.dataSource.findIndex(e => e.expenseID === expense.expenseID);
            this.loadTableData();
            this.cancelEdit();
          });
        }
      };
    }
  }

  cancelEdit(): void {
    this.editingElementID = null;
  }

  deleteElement(expenseID: number): void {
    this.expenseService.deleteExpense(expenseID).subscribe(() => {
      this.dataSource = this.dataSource.filter(e => e.expenseID !== expenseID);
    });
  }

  isEditing(element: Expense): boolean {
    return this.editingElementID === element.expenseID;
  }
}
