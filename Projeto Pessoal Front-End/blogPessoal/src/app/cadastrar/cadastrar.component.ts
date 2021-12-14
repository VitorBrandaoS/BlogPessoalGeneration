import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../model/Usuario';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  usuario: Usuario = new Usuario
  confirmarSenha: string
  tipoUsuario: string
  altura: number
  largura: number

  constructor(
    private authServise: AuthService,
    private router: Router,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0,0)
    this.altura = window.innerHeight
    this.largura = window.innerWidth
    console.log("Altura: "+this.altura)
    console.log("Largura: "+this.largura)
    this.usuario.foto = "https://i.imgur.com/UPgv7SH.png"
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any) {
    this.tipoUsuario = event.target.value
  }

  cadastrar() {
    this.usuario.tipo = this.tipoUsuario

    if(this.usuario.senha != this.confirmarSenha){
      this.alertas.showAlertDanger("Passwords are incorrect!")
    } else if(this.usuario.senha.length < 5){
      this.alertas.showAlertDanger("Password requires minimun 5 characters!")
    } else if(this.usuario.usuario.length < 5){
      this.alertas.showAlertDanger("Username requires minimun 5 characters!")
    } else if(this.usuario.nome.length < 2){
      this.alertas.showAlertDanger("Please verify your name...")
    } else {
      this.authServise.cadastrar(this.usuario).subscribe((resp: Usuario) => {
        this.usuario = resp
        this.router.navigate(["/entrar"])
        this.alertas.showAlertSuccess("User succesfully registered!")
      })
    }

  }

}
