import { Policy } from '../shared/models/policy/policy.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private httpClient: HttpClient) { }

  HTTP_URL = 'http://localhost:8081/api/policy/upload';

  findAll(): Observable<HttpResponse<Policy>> {
    return this.httpClient.get<Policy>(this.HTTP_URL, { observe: 'response' });
  }

}
