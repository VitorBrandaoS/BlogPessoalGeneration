import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UserLogin } from '../model/UserLogin';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url= "https://blog-pessoal-deploy.herokuapp.com"

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  entrar(userLogin: UserLogin): Observable<UserLogin> {
    return this.http.post<UserLogin>(`${this.url}/usuarios/logar`, userLogin)
  }

  cadastrar(usuario: Usuario): Observable<Usuario> {

    return this.http.post<Usuario>(`${this.url}/usuarios/cadastrar`, usuario)
  }

  atualizar(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.url}/usuarios/update`, usuario, this.token)
  }

  atualizarFoto(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.url}/usuarios/update/foto`, usuario, this.token)
  }

  atualizarSenha(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.url}/usuarios/update/senha`, usuario, this.token)
  }

  atualizarUsername(usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.url}/usuarios/update/username`, usuario, this.token)
  }

  getByIdUser(id: number): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.url}/usuarios/${id}`, this.token)
  }

  logado(){
  //let ok: boolean = false

    let ok = false

    if(environment.token != ""){
      ok = true
    }

    return ok

  }

  


}
