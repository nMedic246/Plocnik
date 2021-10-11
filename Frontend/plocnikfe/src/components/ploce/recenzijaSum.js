import {Link} from 'react-router-dom';
import nemaProfilnu from "../../nemaProfilnu.jpg"
import {userService} from '../services/userService'
import React, { Component } from 'react';
import "../css/korisnikPage.css";
import "../css/review.css";


class RecenzijaSum extends Component{
    constructor(props){
        super(props);
        this.state={
            slika:''
        }
    }
    
    componentDidMount(){
        userService.getUserImg(this.props.recenzija.korisnickoIme)
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

    brojZvjezdica(){
        switch(this.props.recenzija.ocjena){
            case 5:
                return(<div className="stars">
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                </div>)
                break;
            case 4:
                return(<div className="stars">
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    </div>)
                break;
            case 3:
                return(<div className="stars">
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    </div>)
                
                break;
            case 2:
                return(<div className="stars">
                   <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    </div>)
                break;
            case 1:
                return(<div className="stars">
                     <i class="material-icons icon-gold"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    <i class="material-icons icon-grey"> star</i>
                    </div>)
                break;
            default:
                break;
        }
    }

render(){
    const {recenzija} = this.props;

    const {slika} = this.state ? (this.state.slika): false
    const prikazSlike = slika ? (
        <div className="pic-container">
        <img class='profile-pic'src ={`data:image/jpeg;base64,${slika}`} />
    </div>):(<div className="pic-container"><img class='profile-pic'src ={nemaProfilnu} /></div>)

    return (
        <div className="row review">
            <div className="col l2">
               {prikazSlike}
            </div>
            <div className="col l7">
                <div><Link to={'/korisnik/'+recenzija.korisnickoIme}><a class="link-text-review">{recenzija.korisnickoIme} </a></Link></div>
                <div>{recenzija.date.substring(0,10)} {recenzija.date.substring(11,16)}</div>
                <br/>
                <div>{recenzija.tekst}</div>
            </div>
            <div className="col l3">
                {this.brojZvjezdica()}
            </div>
        </div>
      )
}
}


export default RecenzijaSum
