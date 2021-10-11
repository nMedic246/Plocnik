import React, { Component } from 'react'
import {userService} from '../services/userService'
import axios from 'axios';
import "../css/korisnikPage.css";
import PlocaProfil from '../ploce/plocaProfil';
import nemaProfilnu from "../../nemaProfilnu.jpg"
import {Link} from 'react-router-dom'


class korisnikPage extends Component{
    constructor(props){
        super(props);
        this.state= {
            korisnik:'',
            selectedFile:null,
            mojePloce:[],
            listaZelja:[],
            slika:''
          }
    }
    componentDidMount(){
        Promise.all([
         userService.getUserByUsername(this.props.match.params.korisnickoIme).then(res=>res.json()),       
         userService.getUserImg(this.props.match.params.korisnickoIme).then(res=>res.json()),
         userService.getUserCollection(this.props.match.params.korisnickoIme).then(res=>res.json()),
         userService.getUserWishList(this.props.match.params.korisnickoIme).then(res=>res.json())
        ]).then(([data1,data2,data3,data4])=>{
            this.setState({
                korisnik:data1,
                slika:data2,
                mojePloce:data3,
                listaZelja:data4
            });
        })
    }

    onFileChange = event => {
        this.setState({ selectedFile: event.target.files[0] });
    };
      
    onFileUpload = () => {
       
        const formData = new FormData();
   
        formData.append(
          "file",
          this.state.selectedFile,
          this.state.selectedFile.name
        );
        axios.post("/korisnik/"+this.state.korisnik.idKorisnik+"/updatePhoto", formData);
        window.location.reload(false);
      };
 
    render(){
        const{korisnik,mojePloce,listaZelja}=this.state;
        const mojePloceList = mojePloce.length ? (
            mojePloce.map(ploca => {
              return(
                <PlocaProfil ploca={ploca.idVinylPloca} plocaProfil={true} key={ploca.idVinylPloca.idVinylPloca}/>
              )
            })
          ):(
            <div>Još nema unesenih ploča</div>
          )

        const mojeListaZelja = listaZelja.length ? (
            listaZelja.map(ploca => {
              return(
                <PlocaProfil ploca={ploca.idVinylPloca} plocaProfil={true} key={ploca.idVinylPloca.idVinylPloca}/>
              )
            })
          ):(
            <div>Još nema unesenih ploča</div>
        )

        let trenutniKorisnik = JSON.parse(localStorage.getItem('user'));
        const {slika} = this.state ? (this.state.slika): false
        var jeTrenutniKorisnik =false;
        if(trenutniKorisnik){
           jeTrenutniKorisnik = trenutniKorisnik.korisnickoIme==korisnik.korisnickoIme ? true:false;
        }
       
        const dodajSliku = slika ? (<div></div>):(  
            <div>
                <div class="btn">
                <span></span>
                <input type="file" onChange={this.onFileChange} />
                </div>
                <button class="btn waves-effect waves-light " onClick={this.onFileUpload}>
                          Upload!
              </button>
            </div>
        )

        const prikazUredi = jeTrenutniKorisnik ? (
           <span >{dodajSliku} <Link to={'/urediProfil/'+trenutniKorisnik.korisnickoIme}><a class="waves-effect waves-light btn-small ">Uredi profil</a></Link>
            
           </span>
            ):(<div></div>)
           
        const prikazSlike = slika ? (
            <div className="pic-container">
            <img class='profile-pic'src ={`data:image/jpeg;base64,${slika}`} />
        </div>):(<div className="pic-container"><img class='profile-pic'src ={nemaProfilnu} /></div>)
       if(korisnik.korisnickoIme){
        return (
          <div className="container ">
              <div className="card grey darken-3">
               <div className="card-content">
                  <div className="profile-card1 row">
                      {prikazSlike}
                      <span class="card-title">{korisnik.korisnickoIme}</span>
                      {prikazUredi}
                      <div>Broj ploča u kolekciji: {mojePloce.length}</div>
                      <div>Broj ploča u listi želja: {listaZelja.length}</div>
                  </div>
                  <div className="profile-card2 row">
                      <div className="ploce">
                          <h6>Moje ploče</h6>
                          {mojePloceList}
                      </div>
                  </div>
                  <div className="profile-card3 row"> 
                  <div className="ploce">
                      <h6>Lista želja</h6>
                      {mojeListaZelja}
                  </div>
                  </div>
                </div>
              </div>
          </div>
        )
       }else{
         return( <div>Ne postoji korisnik s tim imenom</div>)
       }
  }
}

export default korisnikPage;