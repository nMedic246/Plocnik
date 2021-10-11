import {Link} from 'react-router-dom';
import izdavackaKucaService from '../services/izdavackaKucaService';
import React, { Component } from 'react'

class izdavackaKucaSum extends Component{
    constructor(props){
        super(props);
        console.log(props);
        this.state={
            slika:''
        }
    }

    componentDidMount(){
        izdavackaKucaService.getIzdavackaKucaImg(this.props.izdavackaKuca.idIzdavackaKuca)
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
    const {izdavackaKuca} = this.props;
    const {slika} = this.state ? (this.state.slika): false
    const prikazSlike = slika ? (
        <div className="card-image">
          <img src ={`data:image/jpeg;base64,${slika}`} />
        </div>):
     (<div>Nema slike</div>)

    return (
        <div class="col s6 m4 l2 ">
            <Link to={'/izdavackaKuca/'+izdavackaKuca.idIzdavackaKuca}>
                <div class="card small grey darken-3">
                    {prikazSlike}
                    <div class="card-content white-text">
                    <span class="card-title">  {izdavackaKuca.nazivIzdavackaKuca}</span>
                     </div>
                </div>
            </Link>
        </div>
       
            
      )
    }
}

export default izdavackaKucaSum
