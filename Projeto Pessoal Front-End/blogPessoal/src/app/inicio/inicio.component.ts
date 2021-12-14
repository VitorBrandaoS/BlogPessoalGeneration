import { style } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { Usuario } from '../model/Usuario';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  postagemAtual: Postagem = new Postagem()
  listaPostagens: Postagem[]
  tituloPost: string

  tema: Tema = new Tema()
  listaTemas: Tema[]
  idTema: number
  nomeTema: string
  idUsuario = environment.id
  usuario: Usuario = new Usuario()

  key = "data"
  reverse = true

  constructor(
    private router: Router,
    private postagemService: PostagemService,
    private temaService: TemaService,
    private authService: AuthService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {

    window.scroll(0,0)

    if(environment.token == ""){
      //alert("Your session has expired! Please log in again.")
      this.router.navigate(["/entrar"])
    }
    this.findByIdUser()
    this.getAllTemas()
    this.getAllPostagens()
  }

  getAllTemas() {
    this.temaService.getAllTema().subscribe((resp: Tema[]) =>{
      this.listaTemas = resp
    })
  }

  findByIdTema(){
    this.temaService.getByIdTema(this.idTema).subscribe((resp: Tema) => {
      this.tema = resp
    })
  }

  getAllPostagens(){
    this.postagemService.getAllPostagens().subscribe((resp: Postagem[]) => {
      this.listaPostagens = resp
    })
  }

  getByIdPostagens(id: number){
    this.postagemService.getByIdPostagem(id).subscribe((resp: Postagem) => {
      this.postagemAtual = resp
    })
  }

  findByIdUser(){
    this.authService.getByIdUser(this.idUsuario).subscribe((resp: Usuario) => {
      this.usuario = resp
    })
  }

  publicar(){
    this.tema.id = this.idTema
    this.postagem.tema = this.tema
    this.usuario.id = environment.id
    this.postagem.usuario = this.usuario
    
    this.postagemService.postPostagem(this.postagem).subscribe((resp: Postagem) => {
      this.postagem = resp
      this.alertas.showAlertSuccess("Post Successfully!")
      this.postagem = new Postagem()
      this.getAllPostagens()
    })
    
  }

  findByTituloPostagem(){

    if(this.tituloPost == ""){
      this.getAllPostagens()
    }else{
      this.postagemService.getByTituloPostagem(this.tituloPost).subscribe((resp: Postagem[]) =>{
        this.listaPostagens = resp
      })
    }
    
  } 

  findByTituloPostagemUser(){

    if(this.tituloPost == ""){
      this.getAllPostagens()
      this.findByIdUser()
    }else{
      this.postagemService.getByTituloPostagem(this.tituloPost).subscribe((resp: Postagem[]) =>{
        this.usuario.postagem = resp
      })
    }
    
  } 

  findByNomeTema(){
    if(this.nomeTema == ""){
      this.getAllTemas()
    }else{
      this.temaService.getByNomeTema(this.nomeTema).subscribe((resp: Tema[]) => {
        this.listaTemas = resp
      })
    }
  }

  reactionFunction(id: number, value: string){
    this.postagemService.reaction(id, value, environment.id).subscribe((resp: Postagem) =>{
      this.postagem = resp
      this.getAllPostagens()
    })
  }
/*
  verify(id: number){
    this.getByIdPostagens(id)
    let valor = this.postagemAtual.listaCurtidaUsuario.includes(this.usuario)
    return valor
  }

  verifyLike(idPost: number){
    this.postagemService.verifyLike(idPost, this.idUsuario).subscribe(resp =>{
      return resp
    })
  }
*/
}
