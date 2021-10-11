import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private httpClient: HttpClient) { }

  HTTP_URL = 'http://localhost:8081/api/config';

  public getColumnNames(): Observable<HttpResponse<string[]>> {
    return this.httpClient.get<string[]>(`${this.HTTP_URL}/columns`, { observe: 'response' })
  }

}
