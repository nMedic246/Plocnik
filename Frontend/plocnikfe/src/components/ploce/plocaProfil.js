import {Link} from 'react-router-dom';
import plocaService from '../services/plocaService';
import React, { Component } from 'react'
import "../css/Sum.css";

class PlocaProfil extends Component{
    constructor(props){
        super(props);
        this.state={
            slika:''
        }
    }

    componentDidMount(){
        plocaService.getPlocaImg(this.props.ploca.idVinylPloca)
        .then(async response => {
          const data = await response.json();
          // check for error response
          if (!response.ok) {
              // get error message from body or default to response statusText
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
    const {ploca} = this.props;
    const {slika} = this.state ? (this.state.slika): false
    const prikazSlike = slika ? (
        <div className="card-image">
          <img src ={`data:image/jpeg;base64,${slika}`} />
        </div>):
     (<div>Nema slike</div>)

    return (
       
        <div class="col s6 m4 l2 ">
             <Link to={'/vinylPloca/'+ploca.idVinylPloca}>
              <div class="card small grey darken-3" key={ploca.idVinylPloca}>
                {prikazSlike}
            </div>
            </Link>
        </div>
             
      )
}
}


export default PlocaProfil
