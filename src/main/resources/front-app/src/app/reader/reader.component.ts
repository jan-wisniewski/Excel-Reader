import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {

  uploadedFile: any;

  fileForm = this.fb.group({
    file: ['', Validators.required]
  })

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onFileSelected(event: any) {
    this.uploadedFile = event;
  }

  onFileSend() {
    console.log('sending');
  }

  check() {
    console.log(this.fileForm);
    console.log('=======');
    console.log(this.uploadedFile);
  }

}
