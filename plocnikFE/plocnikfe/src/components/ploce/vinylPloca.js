import React, { Component } from 'react'
import plocaService from '../services/plocaService';
import axios from 'axios';
import {Link} from 'react-router-dom'
import PjesmaSum from './pjesmaSum'
import RecenzijaSum from './recenzijaSum'
import '../css/singleCard.css'
import "../css/Card.css"
import {history} from  '../helpers/history';

class vinylPloca extends Component {
  constructor(props){
    super(props);
    let userL = JSON.parse(localStorage.getItem('user'));
    const isLoggedin = userL ? true:false
    this.state= {
      user:userL,
      isLoggedin:isLoggedin,
      ploca:'',
      izvodac:'',
      izdavackaKuca:'',
      vrsta:'',
      pjesme: [],
      selectedFile:null,
      slika:'',
      imaPlocu:[],
      zeliPlocu:[],
      recenzije:[],
      isSelectedM:false,
      isSelectedZ:false,
      tekst:'',
      ocjena:'',
      isSubmitted:false,
      zanr:[]
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  componentDidMount(){
    if(this.state.isLoggedin){
      
        Promise.all([
          plocaService.getPlocaWanters(this.props.match.params.id).then(res=>res.json()),
          plocaService.getPlocaOwners(this.props.match.params.id).then(res=>res.json())
      ]).then(([data5,data6])=>{
          this.setState({
              zeliPlocu:data5,
              imaPlocu:data6
          });
          if (this.state.imaPlocu.some(korisnik => korisnik.idKorisnik.idKorisnik  === this.state.user.idKorisnik)) {
            this.setState({
            isSelectedM : true
            })
          
          }
          if (this.state.zeliPlocu.some(korisnik => korisnik.idKorisnik.idKorisnik  === this.state.user.idKorisnik)) {
            this.setState({
              isSelectedZ : true
            })
          }
      })
     
    }

   Promise.all([
        plocaService.getPlocaById(this.props.match.params.id).then(res=>res.json()),
        plocaService.getPlocaImg(this.props.match.params.id).then(res=>res.json()),
        plocaService.getPlocaPjesme(this.props.match.params.id).then(res=>res.json()),
        plocaService.getPlocaZanr(this.props.match.params.id).then(res=>res.json()),
        plocaService.getPlocaReviews(this.props.match.params.id).then(res=>res.json())
    ]).then(([data1,data2,data3,data4,data5])=>{
        this.setState({
            ploca:data1,
            izvodac:data1.idIzvodac,
            vrsta:data1.idVrsta,
            izdavackaKuca:data1.idIzdavackaKuca,
            slika:data2,
            pjesme:data3,
            zanr:data4,
            recenzije:data5
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

    axios.post("/vinylPloca/"+this.props.match.params.id+"/updatePhoto", formData);
    window.location.reload(false);
  };

  toggleSelectedM = ()=>{
    this.state.isSelectedM=!this.state.isSelectedM;
    if(this.state.isSelectedM){
      plocaService.selectImamPlocu(this.props.match.params.id).then(res=>res.json())
        .then((data)=>{
        })
        window.location.reload(false);
    }else{
    plocaService.deselectImamPlocu(this.props.match.params.id).then(res=>res.json())
      .then((data)=>{
      })
      window.location.reload(false);
    }
  }
  toggleSelectedZ = ()=>{
    this.state.isSelectedZ=!this.state.isSelectedZ;

    if(this.state.isSelectedZ){
      console.log(this.props.match.params.id)
      plocaService.selectZelimPlocu(this.props.match.params.id).then(res=>res.json())
        .then((res)=>{
          console.log(res);
        })
        window.location.reload(false);
    }else{
    plocaService.deselectZelimPlocu(this.props.match.params.id).then(res=>res.json())
      .then((res)=>{
        console.log(res);
      })
      window.location.reload(false);
    }
  }

  handleChange (e) {
    const { name, value } = e.target;
    console.log(e.target);
    this.setState({ [name]: value });
  }

  handleSubmit(e) {
    e.preventDefault();
    this.setState({ isSubmitted: true });
    const {tekst, ocjena,user } = this.state;
    if(ocjena){
      plocaService.postReview(tekst,ocjena,user.korisnickoIme,this.props.match.params.id).then(res=>res.json())
      .then(res=>{
        console.log(res);
      })
      window.location.reload(false);
    }
    
  }
  deleteItem(e){
    e.preventDefault();
    const {ploca}=this.state;
    plocaService.obrisiPlocu(ploca.idVinylPloca).then(res=>res.json())
    .then(res=>{
      console.log(res);
      history.push('/listaPloca');
    });
    
  }
  dodajRecenziju(){
    if(this.state.isLoggedin){
      if(!this.state.user.isAdmin){
        const {error}=this.props;
        const {tekst,isSubmitted,ocjena}=this.state;
        return(
        
            <form  onSubmit={this.handleSubmit}>
              <div className="row review"> 
              <div className="col l3">
                <h6>Postavi recenziju:</h6>
              </div>
              <div className="col l6">
                <div className="row row-review">
                    <span className="rate">
                      <input type="radio" id="star5" name="ocjena" value="5" onChange={this.handleChange} />
                      <label for="star5" title="text">5 stars</label>
                      <input type="radio" id="star4" name="ocjena" value="4" onChange={this.handleChange} />
                      <label for="star4" title="text">4 stars</label>
                      <input type="radio" id="star3" name="ocjena" value="3" onChange={this.handleChange}/>
                      <label for="star3" title="text">3 stars</label>
                      <input type="radio" id="star2" name="ocjena" value="2" onChange={this.handleChange} />
                      <label for="star2" title="text">2 stars</label>
                      <input type="radio" id="star1" name="ocjena" value="1" onChange={this.handleChange} />
                      <label for="star1" title="text">1 star</label>
                    </span>
                  </div> 
                <div className={'form-group ' + (isSubmitted && !ocjena ? ' has-error' : '')}>
                      {isSubmitted && !ocjena &&<span className="help-block red-text">Ocjena je obavezna!</span>}
                    </div>
                
                <label className="label-recenzija" htmlFor="tekst">Tekst recenzije</label>
                <input className="recenzija"type="text" id='tekst' name="tekst" value ={tekst} onChange={this.handleChange} />
              </div>
              <div className="col l3">
                <div className="input-field">
                  <button className="btn">Objavi</button> 
                </div> 
              </div>
          
          </div>
          </form>
        )
      }
    
    }
  }

 render(){
   
  const{ploca,pjesme, izvodac ,izdavackaKuca, zanr, isLoggedin,user,isSelectedM,isSelectedZ,recenzije,vrsta}=this.state;
  const {slika} = this.state ? (this.state.slika): false
  const prikazSlike = slika ? (
        <div className="slika">
          <img src ={`data:image/jpeg;base64,${slika}`} />
        </div>):
  (<div>Nema slike</div>)

  var isAdmin=false;
  var prikaziImam;
  var prikaziZelim;
  var prikaziObrisi;
  
  if(isLoggedin){
      isAdmin = (user.isAdmin) ? (
          <div>
            <br/>
            <div class="btn">
            <span></span>
            <input type="file" onChange={this.onFileChange} />
            </div>
            <button class="btn waves-effect waves-light " onClick={this.onFileUpload}>
                      Upload!
              </button>
             
          </div>
        ):(<div></div>)
      if(!user.isAdmin){
        prikaziImam = isSelectedM ?(
          <button class="btn waves-effect waves-light " onClick={this.toggleSelectedM}>Ukloni iz kolekcije</button>
        ):(<button class="btn waves-effect waves-light " onClick={this.toggleSelectedM}>Dodaj u kolekciju</button>)
        prikaziZelim = isSelectedZ ?(
         <button class="btn waves-effect waves-light" onClick={this.toggleSelectedZ}>Ukloni iz liste želja</button>
       ):(<button class="btn waves-effect waves-light " onClick={this.toggleSelectedZ}>Dodaj na listu želja</button>)
      }else{
        prikaziObrisi = user.isAdmin ?(
        <button  class="btn waves-effect waves-light " onClick={(e) => { if (window.confirm('Jeste li sigurni da želite obrisati ovu ploču?')) this.deleteItem(e) } }>
          Obriši
        </button>
        ):(<div></div>)
      }
     
  }

  const zanrList = zanr.length ? (
    zanr.map(z=>{
      return(
        <span>{z.idZanr.nazivZanr} </span>
      )
    })
  ):(<div></div>)
  const pjesmeList = pjesme.length ? 
        (
          <table>
            <thead>
              <tr>
                  <th></th>
                  <th>Naziv pjesme</th>
                  <th>Trajanje</th>
                  <th>Producent</th>
              </tr>
            </thead>
            <tbody>
            {pjesme.map(pjesma => {
                return (
              <PjesmaSum pjesma={pjesma} key={pjesma.idPjesma}/>   
                )
              })}
            </tbody>
        </table>   
        ):(
            <div>Još nema unesenih pjesama </div>
        )
  const RecenzijeList = recenzije.length ?
          (recenzije.map(recenzija =>{
            return(
              <RecenzijaSum recenzija={recenzija} key={recenzija.idRecenzija}/>
            )
          })):(<div>Još nema recenzija</div>)
    return (
        <div className="container section project-details">
          <div className="card grey darken-3">
            <div className="card-content" key= {ploca.idVinylPloca}>
              <div className="row">
                <div class="col l3">
                  {prikazSlike}
                </div>
       
                <div class="col l6">
                  <span className="card-title"><Link to={'/izvodac/'+izvodac.idIzvodac}><a class="link-text">{izvodac.nazivIzvodac} </a></Link>- {ploca.nazivVinylPloca}</span>
                  <h6>Žanr: {zanrList}</h6>
                  <h6>Godina izdanja: {ploca.godinaIzdanja}</h6>
                  <h6>Izdavačka kuća: <Link to={'/izdavackaKuca/'+izdavackaKuca.idIzdavackaKuca}><a class="link-text">{izdavackaKuca.nazivIzdavackaKuca} </a></Link> </h6>
                  <h6>Vrsta ploče: {vrsta.nazivVrsta}</h6>
                  <h6>Verzija: {ploca.verzija}</h6>
                </div>
                <div class="col l3">
                  <div className="icon">{ploca.ukOcjena}<i class="material-icons gold"> star</i></div>
                  {prikaziImam}
                  {prikaziZelim}
                  {prikaziObrisi}
                </div>
                <br/>
              </div>
              <div className="row"> 
                <p>{ploca.infoVinylPloca}</p>
                {isAdmin}
              </div>
              <div className="row">
                  <h6>Sadržaj:</h6>
                  {pjesmeList}
              </div>
              <h5>Recenzije:</h5>
              <div className="component">
                {this.dodajRecenziju()}
                {RecenzijeList}
              </div>
              
            </div>
          </div>
        </div>
      )
  	}
}
export default vinylPloca;