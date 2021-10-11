import {Link} from 'react-router-dom';
import izvodacService from '../services/izvodacService';
import React, { Component } from 'react'
import "../css/Sum.css";

class IzvodacSum extends Component{
    constructor(props){
        super(props);
        this.state={
            slika:''
        }
    }

    componentDidMount(){
        izvodacService.getIzvodacImg(this.props.izvodac.idIzvodac)
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
    const {izvodac} = this.props;
    const {slika} = this.state ? (this.state.slika): false
    const prikazSlike = slika ? (
        <div className="card-image">
          <img src ={`data:image/jpeg;base64,${slika}`} />
        </div>):
     (<div>Nema slike</div>)

    return (
            <div class="col s6 m4 l2">
                 <Link to={'/izvodac/'+izvodac.idIzvodac}>
                    <div class="card small grey darken-3" key={izvodac.idIzvodac}>
                        {prikazSlike}
                        <div class="card-content white-text">
                            <span class="card-title">  {izvodac.nazivIzvodac}</span>
                        </div>
                    </div>
                </Link>
            </div>
            
      )
    }
}

export default IzvodacSum
