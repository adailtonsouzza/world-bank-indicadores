import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class IndicadorService {
  private API_URL = `${environment.apiUrl}/indicador`;

  constructor(private http: HttpClient) {}


  getIndicador(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}?codigoPais=${codigo}`);
  }
}