import { SnackbarComponent } from './../snackbar/snackbar.component';
import { MatSnackBar } from '@angular/material/snack-bar';
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

  constructor(private fb: FormBuilder, private fileService: FileService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
  }

  openSnackBar() {
    this.snackBar.openFromComponent(SnackbarComponent, {
      duration: 3 * 1000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: ['green-snackbar'],
    });
  }

  onFileSelected(event: any) {
    this.uploadedFile = event.target.files[0];
  }

  onFileSend() {
    this.fileService.upload(this.uploadedFile)
    .subscribe(resp => {
      this.openSnackBar();
    });
  }
}
