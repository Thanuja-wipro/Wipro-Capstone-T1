import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReportService } from '../report.service';

interface Report {
  reportID: number;
  user: User;
  period: string;
  totalAmount: number;
  generatedDate: string;
}

interface Expense {
  expenseID: number;
  amount: number;
  date: string;
  description: string;
  userID: number;
  categoryID: number;
}

interface User {
  userID: number;
  name: string;
  email: string;
  role: string;
  password: string;
  expenses: Expense[];
}

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  reportForm: FormGroup;
  reports: Report[] = [];
  expenses: Expense[] = [];
  userDropdownOptions: User[] = [];
  totalAmountCalc: number = 0;

  constructor(private fb: FormBuilder, private reportService: ReportService) {
    this.reportForm = this.fb.group({
      user: [''],
      startDate: [''],
      endDate: ['']
    });
  }

  ngOnInit(): void {
    this.loadUserDropdownOptions();
    this.loadReports();
  }

  loadUserDropdownOptions(): void {
    this.reportService.getDropdownOptions().subscribe(options => {
      this.userDropdownOptions = options;
    });
  }

  loadReports(): void {
    this.reportService.getReports().subscribe(data => {
      this.reports = data;
    });
  }

  getUserName(userID: number): string {
    const user = this.userDropdownOptions.find(option => option.userID === userID);
    return user ? user.name : 'Unknown';
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

  onSubmit(): void {
    const reportCriteria = {
        ...this.reportForm.value,
        startDate: `${this.reportForm.value.startDate}T00:00:00Z`,
        endDate: `${this.reportForm.value.endDate}T00:00:00Z`
    };

    this.reportService.generateReport(reportCriteria).subscribe(expenses => {
        this.expenses = expenses;
        this.totalAmountCalc = this.calculateTotalAmount(this.expenses);

        const startDate = new Date(this.reportForm.value.startDate);
        const endDate = new Date(this.reportForm.value.endDate);

        const period = this.calculatePeriod(startDate, endDate);

        const newReport: any = {
            user: this.reportForm.value.user,  
            period: period,  
            totalAmount: this.totalAmountCalc,     
            generatedDate: new Date().toISOString() 
        };

        this.reportService.createReport(newReport).subscribe(report => {
          this.loadReports();
          this.reportForm.reset();
        });
    });
}

calculatePeriod(startDate: Date, endDate: Date): string {
    const years = endDate.getFullYear() - startDate.getFullYear();
    const months = endDate.getMonth() - startDate.getMonth();
    const days = Math.floor((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));

    let period = '';
    if (years > 0) {
        period += `${years} year${years > 1 ? 's' : ''} `;
    }
    if (months > 0) {
        period += `${months} month${months > 1 ? 's' : ''} `;
    }
    if (days > 0) {
        period += `${days} day${days > 1 ? 's' : ''}`;
    }
    return period.trim();
}

calculateTotalAmount(expenses: Expense[]): number {
    return expenses.reduce((sum, expense) => sum + expense.amount, 0);
}

deleteReport(index: number): void {
  const deletedReport = this.reports[index];
  console.log('Deleting report:', deletedReport);
  console.log('Deleting report:', );
  
  this.reportService.deleteReport(deletedReport.reportID).subscribe(() => {
    this.loadReports();
  });
}

}