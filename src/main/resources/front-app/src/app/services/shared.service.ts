import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  public HTTP_URL = 'http://excel.bystre.re';

  getUrl(): string {
    return this.HTTP_URL;
  }

  constructor(  ) { }

}
