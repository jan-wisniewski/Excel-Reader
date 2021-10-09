import { SnackbarComponent } from './../snackbar/snackbar.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Policy } from '../shared/models/policy/policy.model';
import { FileService } from './../services/file.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { faPencilAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {

  uploadedFile: any;
  pencil = faPencilAlt;
  policies: Policy[] = []

  fileForm = this.fb.group({
    file: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private fileService: FileService, private snackBar: MatSnackBar) {}

  ngOnInit(): void {
  }

  openSnackBar() {
    this.snackBar.openFromComponent(SnackbarComponent, {
      duration: 5 * 1000,
    });
  }

  onFileSelected(event: any) {
    this.uploadedFile = event.target.files[0];
  }

  onFileSend() {
    this.fileService.upload(this.uploadedFile)
    .subscribe(resp => {
      this.getAllPolicies();
      this.openSnackBar();
    });
  }

  getAllPolicies() {
    this.fileService.findAll()
    .subscribe(resp => {
      if (resp.body) {
        console.log(resp);
        this.policies = resp?.body;
        console.log(this.policies);
      }
    });
  }

}
