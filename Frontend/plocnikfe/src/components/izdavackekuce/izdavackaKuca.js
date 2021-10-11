import React, { Component } from 'react'
import izdavackaKucaService from '../services/izdavackaKucaService';
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../css/Card.css"
import '../css/singleCard.css'
import {history} from '../helpers/history';

class izdavackaKuca extends Component{
    constructor(props){
        super(props);
        let userL = JSON.parse(localStorage.getItem('user'));
        const isLoggedin = userL ? true:false
        this.state={
          user:userL,
          isLoggedin:isLoggedin,
          mjesto:'',
          izdavackaKuca:'',
          vlasnik:'',
          selectedFile:null,
          ploce:[],
          slika:''
        }
    }
    componentDidMount(){
        Promise.all([
            izdavackaKucaService.getIzdavackaKucaById(this.props.match.params.id).then(res=>res.json()),
            izdavackaKucaService.getIzdavackaKucaImg(this.props.match.params.id).then(res=>res.json()),
            izdavackaKucaService.getIzdavackaKucaPloce(this.props.match.params.id).then(res=>res.json())
        ]).then(([data1,data2,data3])=>{
            this.setState({
                izdavackaKuca:data1,
                mjesto:data1.idMjesto,
                slika:data2,
                vlasnik:data1.idVlasnik,
                ploce:data3
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

        axios.post("/izdavackaKuca/"+this.props.match.params.id+"/updatePhoto", formData);
        window.location.reload(false);
    };
    deleteItem(e){
      console.log("tu sam");
      e.preventDefault();
      const {izdavackaKuca}=this.state;
       izdavackaKucaService.obrisiKucu(izdavackaKuca.idIzdavackaKuca).then(res=>res.json())
      .then(res=>{
        console.log(res);
        history.push('/listaIzdavackihKuca');
      });
    }
    render(){
        const{izdavackaKuca,mjesto,vlasnik,ploce,user,isLoggedin} = this.state;
        var obrisiKucu;
        if(isLoggedin){
          obrisiKucu = user.isAdmin? ( <button  class="btn waves-effect waves-light " onClick={(e) => { if (window.confirm('Jeste li sigurni da želite obrisati ovu izdavačku kuću?')) this.deleteItem(e) } }>
          Obriši
        </button>):(<div></div>)
        }

        const popisPloca = ploce.length ? (
          <table>
          <thead>
            <tr>
              <th>Godina izdanja</th>
              <th>Naziv ploče</th>
            </tr>
          </thead>
          <tbody>
              {ploce.map(ploca=>{
            return(
              <tr>
              <td>{ploca.godinaIzdanja}.</td>    
              <td><Link to={'/vinylPloca/'+ploca.idVinylPloca}><a class="link-text">{ploca.nazivVinylPloca} </a></Link></td>
              </tr>
            )
          })}
          </tbody>
         </table>
          
        ):(<div>Za sad nema objavljenih albuma.</div>)

        const {slika} = this.state ? (this.state.slika): false
        const prikazSlike = slika ? (
            <div className="slika">
              <img src ={`data:image/jpeg;base64,${slika}`} />
            </div>):
        (<div>Nema slike</div>)

        var isAdmin=false;
     
        if(isLoggedin){
            isAdmin = (user.isAdmin) ? (
                <div>
                  <br/>
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
        }
        
        return (
            <div className="container section project-details">
              <div className="card grey darken-3">
                <div className="card-content" key= {izdavackaKuca.idIzdavackaKuca}>
                  <div className="row">
                    <div class="col l3">
                      {prikazSlike}
                    </div>
                    <div class="col l6">
                      <span className="card-title">{izdavackaKuca.nazivIzdavackaKuca} </span>
                      <h6>Lokacija : {mjesto.nazivMjesto}</h6>
                      <h6>Vlasnik : {vlasnik.nazivVlasnik}</h6>
                      <br/>
                    </div>
                    <div className="col l3">
                      {obrisiKucu}
                    </div>
                  </div>
                  <div className="row"> 
                    <p>{izdavackaKuca.infoIzdavackaKuca}</p>
                    {isAdmin}
                  </div>
                  <div className="row">
                    <h6>Popis ploča: </h6>
                      {popisPloca}
                  </div> 
                </div>
              </div>
            </div>
          )
  }
}

export default izdavackaKuca;