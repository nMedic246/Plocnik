import React, { Component } from 'react'
import izvodacService from '../services/izvodacService';
import izdavackaKucaService from '../services/izdavackaKucaService'
import plocaService from '../services/plocaService';
import ReactDOM from 'react-dom'; 
import $ from 'jquery';

import '../css/singleCard.css'
import "../css/Card.css"
import M from 'materialize-css'

class addPloca extends Component {
  constructor(props){
    super(props);
    let userL = JSON.parse(localStorage.getItem('user'));
    const isLoggedin = userL ? true:false
    var isAdmin=null
    if(isLoggedin){
      isAdmin=userL.isAdmin ? true:false
    }
    this.state= {
      user:userL,
      isAdmin:isAdmin,
      izvodaci:[],
      izdavackeKuce:[],
      vrste:[],
      zanrovi:[],
      isSubmitted:false,
      nazivVinylPloca:'',
      godinaIzdanja:null,
      verzija:'',
      infoVinylPloca:'',
      idIzdavackaKuca:null,
      idIzvodac:null,
      idVrsta:null,
      idZanr:null
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  componentDidMount(){
    M.AutoInit()
  	  
	  $(ReactDOM.findDOMNode(this.refs.selectIzvodac)).on('change',this.handleSelectIzvodac.bind(this));
    $(ReactDOM.findDOMNode(this.refs.selectIzdavackaKuca)).on('change',this.handleSelectIzdavackaKuca.bind(this));
    $(ReactDOM.findDOMNode(this.refs.selectVrsta)).on('change',this.handleSelectVrsta.bind(this));
    $(ReactDOM.findDOMNode(this.refs.selectZanr)).on('change',this.handleSelectZanr.bind(this));
    izvodacService.getIzvodaci()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ izvodaci:data })
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
            console.error('There was an error!', error);
        });
    izdavackaKucaService.getIzdavackeKuce()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ izdavackeKuce:data })
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
            console.error('There was an error!', error);
        });
    plocaService.getVrstePloca()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ vrste:data })
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
            console.error('There was an error!', error);
        });
    plocaService.getZanrs()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ zanrovi:data })
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
            console.error('There was an error!', error);
        });
  }

 componentDidUpdate() {
    M.AutoInit();
    this.handleChange = this.handleChange.bind(this);
  }

  handleSelectIzvodac(event) {
    event.preventDefault();
    var value = $("#selectIzvodacV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
  }
  handleSelectIzdavackaKuca(event) {
    event.preventDefault();
    var value = $("#selectIzdavackaKucaV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
  
  }
  handleSelectVrsta(event) {
    event.preventDefault();
    var value = $("#selectVrstaV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
  }
  handleSelectZanr(event) {
    event.preventDefault();
    var value = $("#selectZanrV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
  }

  handleChange (e) {
    const { name, value } = e.target;
    this.setState({ [name]: value });
  }
  
  handleSubmit(e) {
     e.preventDefault();
     const {nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,idIzdavackaKuca,idIzvodac,idVrsta,idZanr } = this.state;
    if(nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,idIzdavackaKuca,idIzvodac,idVrsta){
      plocaService.dodajPlocu(nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,idIzdavackaKuca,idIzvodac,idVrsta,idZanr).then(res=>res.json())
      .then(res=>{
        console.log(res);
        this.setState({
          isSubmitted:true,
          nazivVinylPloca:'',
          godinaIzdanja:null,
          verzija:'',
          infoVinylPloca:'',
          idIzdavackaKuca:null,
          idIzvodac:null,
          idVrsta:null,
          idZanr:null
        })
      })
      
    }
     
   }
 render(){
   
   const {izvodaci,izdavackeKuce,isAdmin,vrste,nazivVinylPloca,godinaIzdanja,verzija,infoVinylPloca,zanrovi,isSubmitted} = this.state;
   if(isAdmin){
    return (
      <div className="container section project-details">
        <div className="card grey darken-3">
          <div className="card-content" >
            <div className="row">
              <h5>Dodaj vinyl ploču</h5>
             <form className="col s12" onSubmit={this.handleSubmit}>
              <div className=" row">
                <div class="input-field col s4">
                    <input id="nazivVinylPloca" type="text" name="nazivVinylPloca" value={nazivVinylPloca} onChange={this.handleChange}/>
                    <label for="nazivVinylPloca">Naziv</label>
                </div>
                <div class="input-field col s4">
                    <input id="godinaIzdanja" type="text" name="godinaIzdanja" value={godinaIzdanja} onChange={this.handleChange}/>
                    <label for="godinaIzdanja">Godina izdanja</label>
                </div>
                <div class="input-field col s4">
                    <input id="verzija" type="text" name="verzija" value={verzija} onChange={this.handleChange}/>
                    <label for="verzija">Verzija ploče</label>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <textarea id="infoVinylPloca" class="materialize-textarea" name="infoVinylPloca" value={infoVinylPloca} onChange={this.handleChange}></textarea>
                  <label for="infoVinylPloca">Dodatne informacije o ploči</label>
                </div>
              </div>
              <div className="row">
                <div class="input-field col s6">
                    <select id="selectIzvodacV" ref="selectIzvodac" name="idIzvodac">
                    <option value="" disabled selected>Izvodač</option>
                      { izvodaci.map(izvodac => {
                            return (
                                <option value={izvodac.idIzvodac}>{izvodac.nazivIzvodac}</option>
                            )
                        })}
                    </select>
                    <label>Odaberi izdvođača</label>
                   
                </div>
                <div class="input-field col s6">
                    <select id="selectIzdavackaKucaV" ref="selectIzdavackaKuca" name="idIzdavackaKuca">
                    <option value="" disabled selected>Izdavačka kuća</option>
                      { izdavackeKuce.map(izdavackaKuca => {
                            return (
                                <option value={izdavackaKuca.idIzdavackaKuca}>{izdavackaKuca.nazivIzdavackaKuca}</option>
                            )
                        })}
                    </select>
                    <label>Odaberi izdavačku kuću</label>
                </div>            
              </div>
              <div className="row">
                <div class="input-field col s6">
                    <select id="selectVrstaV" ref="selectVrsta" name="idVrsta">
                    <option value="" disabled selected>Vrsta ploče</option>
                      { vrste.map(vrsta => {
                            return (
                                <option value={vrsta.idVrsta}>{vrsta.nazivVrsta}</option>
                            )
                        })}
                    </select>
                    <label>Odaberi vrstu ploče</label>
                </div>
                <div class="input-field col s6">
                    <select id="selectZanrV" ref="selectZanr" name="idZanr">
                    <option value="" disabled selected>Žanr</option>
                      { zanrovi.map(zanr => {
                            return (
                                <option value={zanr.idZanr}>{zanr.nazivZanr}</option>
                            )
                        })}
                    </select>
                    <label>Odaberi žanr</label>
                </div>
                <div className="col l6">
                  <div className="input-field">
                    <button className="btn">Dodaj ploču</button> 
                  </div> 
                </div>
              </div>
              <row>
                {isSubmitted && <p>Uspješno dodana ploča</p>}
              </row>
             </form>
            </div>    
          </div>
        </div>
      </div>
    )
   }else{
     return (<div>
       Ne biste smjeli biti ovdje!
     </div>)
   }
   
  }
}
export default addPloca;