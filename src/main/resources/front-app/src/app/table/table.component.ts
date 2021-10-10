import { Policy } from './../shared/models/policy/policy.model';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { faExclamationCircle } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { SnackBarService } from './../services/snackbar.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  displayedColumns: string[] = ['number', 'type', 'sum', 'name', 'surname', 'object', 'description'];
  dataSource: Policy[] = [];
  invalidPolicies: number = 0;
  exclamationIcon = faExclamationCircle;
  trashIcon = faTrash;

  constructor(private fileService: FileService, private snackBarService: SnackBarService) { }

  ngOnInit(): void {
    this.findAllPolicies();
    this.updateTable();
  }

  updateTable(): void {
  this.fileService.refresh$.subscribe( () => {
        this.findAllPolicies();
      });
  }

  deleteAll(): void {
    this.fileService.deleteAll().subscribe( res => {
    this.updateTable();
    this.snackBarService.openSnackBar('success', 'green-snackbar', 'Dane zostały usunięte poprawnie');
    });
  }

  findAllPolicies() {
    this.fileService.findAll().subscribe(res => {
      if (res.body) {
        this.dataSource = res?.body;
        this.invalidPolicies = this.dataSource.filter(policy => policy?.valid).length;
      }
    });
  }

}
