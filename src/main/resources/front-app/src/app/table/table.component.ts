import { Policy } from './../shared/models/policy/policy.model';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { faExclamationCircle } from '@fortawesome/free-solid-svg-icons';

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

  constructor(private fileService: FileService) { }

  ngOnInit(): void {
    this.findAllPolicies();
    this.fileService.refresh$.subscribe( () => {
      this.findAllPolicies();
    });
  }

  private findAllPolicies() {
    this.fileService.findAll().subscribe(res => {
      if (res.body) {
        this.dataSource = res?.body;
        this.invalidPolicies = this.dataSource.filter(policy => policy?.valid).length;
      }
    });
  }

}
