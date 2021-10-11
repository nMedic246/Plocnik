import React, { Component } from 'react'
import izvodacService from '../services/izvodacService'
import ReactDOM from 'react-dom'; 
import $ from 'jquery';

import '../css/singleCard.css'
import "../css/Card.css"
import M from 'materialize-css'

class addIzvodac extends Component {
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
      drzave:[],
      nazivIzvodac:'',
      infoIzvodac:'',
      pocDjelovanjaGrupa:'',
      krajDjelovanjaGrupa:'',
      datumRod:null,
      datumSmrti:null,
      idDrzava:null,
      jeGrupa:0
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  componentDidMount(){
    M.AutoInit()

	$(ReactDOM.findDOMNode(this.refs.selectDrzava)).on('change',this.handleSelectDrzava.bind(this));
  
    izvodacService.getDrzave()
    .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
            this.setState({ drzave:data})
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

  handleSelectDrzava(event) {
    event.preventDefault();
    var value = $("#selectDrzavaV").find(":selected").val();
    var name = event.target.name;
    this.setState({ [name]: value });
  }


  handleChange (e) {
    const { name, value } = e.target;
    if(name==='pocDjelovanjaGrupa'){
        this.setState({
            [name]:value,
            jeGrupa:1
        })
    }else{
        this.setState({ [name]: value});
    } 
  }
  
  handleSubmit(e) {
     e.preventDefault();
     
     const {nazivIzvodac,infoIzvodac,idDrzava,pocDjelovanjaGrupa,krajDjelovanjaGrupa,datumRod,datumSmrti,jeGrupa} = this.state;
  
    if(nazivIzvodac,infoIzvodac,idDrzava){
      izvodacService.dodajIzvodaca(nazivIzvodac,infoIzvodac,idDrzava,pocDjelovanjaGrupa,krajDjelovanjaGrupa,datumRod,datumSmrti,jeGrupa).then(res=>res.json())
      .then(res=>{
        this.setState({
          isSubmitted:true,
        
        })
      }) 
    }
   }
 render(){
   
   const {isAdmin,drzave ,nazivIzvodac,infoIzvodac,isSubmitted,krajDjelovanjaGrupa,pocDjelovanjaGrupa,datumRod,datumSmrti} = this.state;
   if(isAdmin){
    return (
      <div className="container section project-details">
        <div className="card grey darken-3">
          <div className="card-content" >
            <div className="row">
              <h5>Dodaj izvođača</h5>
             <form className="col s12" onSubmit={this.handleSubmit}>
              <div className=" row">
                <div class="input-field col s4">
                    <input id="nazivIzvodac" type="text" name="nazivIzvodac" value={nazivIzvodac} onChange={this.handleChange}/>
                    <label for="nazivIzvodac">Naziv</label>
                </div>
              </div>
              <div class="row">
                <div class="input-field col s12">
                  <textarea id="infoIzvodac" class="materialize-textarea" name="infoIzvodac" value={infoIzvodac} onChange={this.handleChange}></textarea>
                  <label for="infoIzvodac">Dodatne informacije izvođaču</label>
                </div>
              </div>
              <div className="row">
                <div class="input-field col s6">
                    <select id="selectDrzavaV" ref="selectDrzava" name="idDrzava">
                    <option value="" disabled selected>Drzava</option>
                      { drzave.map(drzava => {
                            return (
                                <option value={drzava.idDrzava}>{drzava.nazivDrzava}</option>
                            )
                        })}
                    </select>
                   
                </div>     
              </div>
              <div class="card-tabs">
                <ul class="tabs tabs-fixed-width">
                    <li class="tab"><a class="active" href="#test4">Grupa</a></li>
                    <li class="tab"><a  href="#test5">Glazbenik</a></li>
                </ul>
              </div>
                    <div class="card-content">
                    <div id="test4">
                        <div className=" row">
                            <div class="input-field col s4">
                                <input id="pocDjelovanjaGrupa" type="text" name="pocDjelovanjaGrupa" value={pocDjelovanjaGrupa} onChange={this.handleChange}/>
                                <label for="pocDjelovanjaGrupa">Početak djelovanja grupe</label>
                            </div>
                        </div>
                        <div className="row">
                            <div class="input-field col s4">
                                <input id="krajDjelovanjaGrupa" type="text" name="krajDjelovanjaGrupa" value={krajDjelovanjaGrupa} onChange={this.handleChange}/>
                                <label for="krajDjelovanjaGrupa">Kraj djelovanja grupe</label>
                            </div>
                        </div>          
                    </div>
                    <div id="test5">
                        <div className="row">
                            <div className="col s4 offset-s6">
                            <label for="datumRod" >Datum rođenja</label>
                            <input type="date" name="datumRod" value={datumRod} onChange={this.handleChange}/>
                               
                            </div>
                        </div>
                        <div className="row">
                            <div className="col s4 offset-s6">
                                <label for="datumSmrti" type="text" >Datum smrti</label>
                                <input type="date" name="datumSmrti" value={datumSmrti} onChange={this.handleChange}/>
                            </div>
                        </div>
                    </div>
             </div>      

                <div className="col l6">
                  <div className="input-field">
                    <button className="btn">Dodaj izvođača</button> 
                  </div> 
                </div>
            
              <row>
                {isSubmitted && <p>Uspješno dodan novi izvođač</p>}
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
export default addIzvodac;