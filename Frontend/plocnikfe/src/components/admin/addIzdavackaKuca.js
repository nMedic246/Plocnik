import React, { Component } from 'react'

import izdavackaKucaService from '../services/izdavackaKucaService'
import ReactDOM from 'react-dom'; 
import $ from 'jquery';

import '../css/singleCard.css'
import "../css/Card.css"
import M from 'materialize-css'

class addIzdavackaKuca extends Component {
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
      mjesta:[],
      vlasnici:[],
      isSubmitted:false,
      nazivIzdavackaKuca:'',
      infoIzdavackaKuca:'',
      idMjesto:null,
      idVlasnik:null
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  componentDidMount(){
    M.AutoInit()
  	  
	$(ReactDOM.findDOMNode(this.refs.selectMjesto)).on('change',this.handleSelectMjesto.bind(this));
    $(ReactDOM.findDOMNode(this.refs.selectVlasnik)).on('change',this.handleSelectVlasnik.bind(this));

    izdavackaKucaService.getMjesta()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ mjesta:data })
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
            console.error('There was an error!', error);
        });
    izdavackaKucaService.getVlasnici()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ vlasnici:data })
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

  handleSelectMjesto(event) {
    event.preventDefault();
    var value = $("#selectMjestoV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
    console.log(this.state)
  }
  handleSelectVlasnik(event) {
    event.preventDefault();
    var value = $("#selectVlasnikV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
    console.log(this.state)
  }

  handleChange (e) {
    const { name, value } = e.target;
    console.log(e.target)
    this.setState({ [name]: value });
  }
  
  handleSubmit(e) {
     e.preventDefault();
     const {nazivIzdavackaKuca,infoIzdavackaKuca,idMjesto,idVlasnik } = this.state;
    if(nazivIzdavackaKuca,infoIzdavackaKuca,idMjesto,idVlasnik){
      izdavackaKucaService.dodajIzdavackuKucu(nazivIzdavackaKuca,infoIzdavackaKuca,idMjesto,idVlasnik).then(res=>res.json())
      .then(res=>{
        this.setState({
          isSubmitted:true,
          nazivIzdavackaKuca:'',
          infoIzdavackaKuca:'',
          idMjesto:null,
          idVlasnik:null
        })
      })
      
    }
     
   }
 render(){
   
   const {infoIzdavackaKuca,nazivIzdavackaKuca,vlasnici,mjesta,isSubmitted,isAdmin} = this.state;
   if(isAdmin){
    return (
      <div className="container section project-details">
        <div className="card grey darken-3">
          <div className="card-content" >
            <div className="row">
              <h5>Dodaj izdavačku kuću</h5>
             <form className="col s12" onSubmit={this.handleSubmit}>
              <div className=" row">
                <div class="input-field col s4">
                    <input id="nazivIzdavackaKuca" type="text" name="nazivIzdavackaKuca" value={nazivIzdavackaKuca} onChange={this.handleChange}/>
                    <label for="nazivIzdavackaKuca">Naziv</label>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <textarea id="infoIzdavackaKuca" class="materialize-textarea" name="infoIzdavackaKuca" value={infoIzdavackaKuca} onChange={this.handleChange}></textarea>
                  <label for="infoIzdavackaKuca">Dodatne informacije o izdavačkoj kući</label>
                </div>
              </div>
              <div className="row">
                <div class="input-field col s6">
                    <select id="selectMjestoV" ref="selectMjesto" name="idMjesto">
                    <option value="" disabled selected>Mjesto</option>
                      { mjesta.map(mjesto => {
                            return (
                                <option value={mjesto.idMjesto}>{mjesto.nazivMjesto}</option>
                            )
                        })}
                    </select>
                   
                </div>
                <div class="input-field col s6">
                    <select id="selectVlasnikV" ref="selectVlasnik" name="idVlasnik">
                    <option value="" disabled selected>Vlasnik</option>
                      { vlasnici.map(vlasnik => {
                            return (
                                <option value={vlasnik.idVlasnik}>{vlasnik.nazivVlasnik}</option>
                            )
                        })}
                    </select>
                    <label>Odaberi vlasnika</label>
                </div>            
              </div>
    
                <div className="col l6">
                  <div className="input-field">
                    <button className="btn">Dodaj izdavačku kuću</button> 
                  </div> 
                </div>
            
              <row>
                {isSubmitted && <p>Uspješno dodana izdavačka kuća</p>}
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
export default addIzdavackaKuca;