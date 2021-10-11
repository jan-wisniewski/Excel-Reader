import { ConfigService } from './../services/config.service';
import { SnackBarService } from './../services/snackbar.service';
import { Policy } from '../shared/models/policy/policy.model';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { faPencilAlt } from '@fortawesome/free-solid-svg-icons';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {

  uploadedFile: any;
  pencilIcon = faPencilAlt;
  infoIcon = faInfoCircle;
  policies: Policy[] = []

  fileForm = this.fb.group({
    file: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private fileService: FileService, private snackBarService: SnackBarService, private configService: ConfigService) {}

  errorsMessage = '';
  invalidFile = false;
  columnNames: string[] = [];

  ngOnInit(): void {
    this.configService.getColumnNames().subscribe(res => {
      if (res && res?.body) {
        this.columnNames = res.body;
      }
    })
  }

  onFileSelected(event: any) {
    this.uploadedFile = event.target.files[0];
  }

  onFileSend() {
    this.fileService.upload(this.uploadedFile)
    .subscribe(resp => {
      this.snackBarService.openSnackBar('success', 'green-snackbar', 'Dane zostały zapisane poprawnie');
    },
    (exception) => {
      this.invalidFile = true;
      console.log(exception?.error?.message);
      this.errorsMessage = exception?.error?.message;
      this.snackBarService.openSnackBar('warning', 'red-snackbar', this.errorsMessage);
  });
  }
}
