import {Link} from 'react-router-dom';
import {userService} from '../services/userService';
import React, { Component } from 'react'
import "../css/Sum.css";
import korisnikPage from './korisnikPage';
import nemaProfilnu from "../../nemaProfilnu.jpg"

class KorisnikSum extends Component{
    constructor(props){
        super(props)
        this.state={
            slika:''
        }
    }

    componentDidMount(){
        userService.getUserImg(this.props.korisnik.korisnickoIme)
        .then(async response => {
          const data = await response.json();
          if (!response.ok) {
              const error = (data && data.message) || response.statusText;
              return Promise.reject(error);
          }
          this.setState({ slika:data })
      })
      .catch(error => {
          this.setState({ errorMessage: error.toString() });
          console.error('There was an error!', error);
      });
    }

    render(){
        const {korisnik} = this.props;
        const{slika} = this.state ? (this.state.slika): false
        const prikazSlike = slika ? (
            <div className="pic-container">
            <img class='profile-pic'src ={`data:image/jpeg;base64,${slika}`} />
        </div>):(<div className="pic-container"><img class='profile-pic'src ={nemaProfilnu} /></div>)

        return(
            <div className="col s4 box-pink"> 
                <Link to={'/korisnik/'+korisnik.korisnickoIme}>
                    {prikazSlike}
                    <h6>{korisnik.korisnickoIme}</h6>
                   <h6>Broj ploča: {korisnik.brojPloca} </h6>
                </Link> 
            </div>
            
        )
    }
}

export default KorisnikSum;