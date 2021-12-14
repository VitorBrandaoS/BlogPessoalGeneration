import { Tema } from "./Tema"
import { Usuario } from "./Usuario"

export class Postagem {
    public id: number
    public imagem: string
    public titulo: string
    public texto: string
    public data: Date
    public usuario: Usuario
    public curtida: number
    public amou: number
    public tema: Tema
    public listaCurtidaUsuario: Usuario[]
    public listaAmouUsuario: Usuario[]
}