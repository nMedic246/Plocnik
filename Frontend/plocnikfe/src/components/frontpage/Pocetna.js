import React from 'react';
import { Component } from 'react';
import {connect} from "react-redux";
import  {Link} from 'react-router-dom';
import plocaService from '../services/plocaService';
import {userService} from '../services/userService'
import PlocaProfil from '../ploce/plocaProfil';
import KorisnikSum from '../korisnik/korisnikSum'
import './pocetna.css'

class Pocetna extends Component{
    constructor(props){
        super(props);
        this.state={
            ploceP:[],
            ploceW:[],
            ploceO:[],
            kolekcionari:[]
        }
    }
    componentDidMount(){
        Promise.all([
            plocaService.getMostPopular().then(res=>res.json()),
            plocaService.getMostWanted().then(res=>res.json()),
            plocaService.getBestGraded().then(res=>res.json()),
            userService.getBiggestRecordOwners().then(res=>res.json())      
        ]).then(([data1,data2,data3,data4])=>{
            this.setState({
                ploceP:data1,
                ploceW:data2,
                ploceO:data3,
                kolekcionari:data4
            });
        })
    }
    render(){
        const {ploceP,ploceW,ploceO,kolekcionari}= this.state;

        return(
            <div className="container">
                <div className="box">
                    <div className="row row-frontPage">
                    <Link to={'/listaPloca'}><a class="waves-effect waves-light btn-large ">Započni pretragu!</a></Link>
                    </div>
                    <div className="row row-frontPage">
                        <h6>Najpopularnije ploče:</h6>
                        {ploceP.map(ploca=>{
                                return(<PlocaProfil ploca={ploca} plocaProfil={true} key={ploca.idVinylPloca}/>)
                        })
                        }
                    </div>
                    <div className="row row-frontPage">
                       <h6>Najtraženije ploče:</h6>
                       {ploceW.map(ploca=>{
                                 return(<PlocaProfil ploca={ploca} plocaProfil={true} key={ploca.idVinylPloca}/>)
                            })
                            }         
                    </div>
                    <div className=" row row-frontPage"> 
                    <h6>Najbolje ocijenjene:</h6>
                       {ploceO.map(ploca=>{
                                 return(<PlocaProfil ploca={ploca} plocaProfil={true} key={ploca.idVinylPloca}/>)
                            })
                            }       
                    </div>
                    <div className=" row row-frontPage"> 
                    <h6>Najveći kolekcionari:</h6>
                       {kolekcionari.map(korisnik=>{
                                 return(<KorisnikSum korisnik={korisnik}  key={korisnik.idKorisnik}/>)
                            })
                            }       
                    </div>
                </div>
            </div>
        )
    }
  
}


function mapStateToProps(state){
    const{loggedIn} = state.authentication;
    return {loggedIn}
}

export default connect(mapStateToProps)(Pocetna);
