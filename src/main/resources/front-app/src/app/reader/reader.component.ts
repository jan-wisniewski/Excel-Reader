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

  fileForm = this.fb.group({
    file: ['', Validators.required]
  })

  constructor(private fb: FormBuilder, private fileService: FileService) {}

  ngOnInit(): void {
  }

  onFileSelected(event: any) {
    this.uploadedFile = event.target.files[0];
  }

  onFileSend() {
    this.fileService.upload(this.uploadedFile)
    .subscribe(resp => {
      console.log(resp);
    });
  }

}
