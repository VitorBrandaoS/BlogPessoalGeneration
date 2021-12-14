import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {

  url= "https://blog-pessoal-deploy.herokuapp.com"

  constructor(private http: HttpClient) { 

  }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllPostagens(): Observable<Postagem[]> {
    return this.http.get<Postagem[]>(`${this.url}/postagens`, this.token)
  }

  getByIdPostagem(id: number): Observable<Postagem>{
    return this.http.get<Postagem>(`${this.url}/postagens/${id}`, this.token)
  }

  getByTituloPostagem(titulo: string): Observable<Postagem[]>{
    return this.http.get<Postagem[]>(`${this.url}/postagens/titulo/${titulo}`, this.token)
  }

  postPostagem(postagem: Postagem) : Observable<Postagem> {
    return this.http.post<Postagem>(`${this.url}/postagens`, postagem, this.token)
  }

  putPostagem(postagem: Postagem): Observable<Postagem> {
    return this.http.put<Postagem>(`${this.url}/postagens/update`, postagem)
  }

  deletePostagem(id: number) {
    return this.http.delete(`${this.url}/postagens/${id}`, this.token)
  }

  reaction(idPost: number, value: string, idUser: number): Observable<Postagem> {
    return this.http.post<Postagem>(`${this.url}/postagens/reaction/${idPost}/${value}/${idUser}`, this.token)
  }

  verifyLike(idPost: number, idUser: number) {
    return this.http.post(`${this.url}/postagens/verify/like/${idPost}/${idUser}`, this.token)
  }

}
