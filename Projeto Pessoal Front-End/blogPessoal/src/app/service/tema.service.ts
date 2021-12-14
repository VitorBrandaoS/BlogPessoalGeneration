import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  url= "https://blog-pessoal-deploy.herokuapp.com"

  constructor(private http: HttpClient) { 

  }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllTema(): Observable<Tema[]>{
    return this.http.get<Tema[]>(`${this.url}/tema`, this.token)

  }

  getByIdTema(id: number): Observable<Tema> {
    return this.http.get<Tema>(`${this.url}/tema/${id}`, this.token)
  }

  getByNomeTema(nome: string): Observable<Tema[]> {
    return this.http.get<Tema[]>(`${this.url}/tema/nome/${nome}`, this.token)
  }

  postTema(tema: Tema): Observable<Tema>{
    return this.http.post<Tema>(`${this.url}/tema`, tema, this.token)
  }

  putTema(tema: Tema): Observable<Tema> {
    return this.http.put<Tema>(`${this.url}/tema`, tema, this.token)
  }

  deleteTema(id: number) {
    return this.http.delete(`${this.url}/tema/${id}`, this.token)
  }


}
