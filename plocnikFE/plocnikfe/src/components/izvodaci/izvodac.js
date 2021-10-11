import React, { Component } from 'react'
import izvodacService from '../services/izvodacService';
import axios from 'axios';
import {Link} from 'react-router-dom';
import "../css/Sum.css";
import '../css/singleCard.css'
import {history} from '../helpers//history'

class izvodac extends Component{
    constructor(props){
        super(props);
        this.state={
            grupa:'',
            glazbenik:'',
            drzava:'',
            izvodac:'',
            ploce:[],
            selectedFile:null,
            slika:''
        }
    }
    componentDidMount(){
        Promise.all([
            izvodacService.getIzvodacById(this.props.match.params.id).then(res=>res.json()),
            izvodacService.getIzvodacImg(this.props.match.params.id).then(res=>res.json()),
            izvodacService.getGrupaByIdIzvodac(this.props.match.params.id).then(res=>res.json()),
            izvodacService.getGlazbenikByIdIzvodac(this.props.match.params.id).then(res=>res.json()),
            izvodacService.getIzvodacPloce(this.props.match.params.id).then(res=>res.json())
        ]).then(([data1,data2,data3,data4,data5])=>{
            this.setState({
                izvodac:data1,
                drzava:data1.idDrzava,
                slika:data2,
                glazbenik:data4,
                grupa:data3,
                ploce:data5
            });
        })
    }

    onFileChange = event => {
    
        // Update the state
        this.setState({ selectedFile: event.target.files[0] });
      
      };
      
      onFileUpload = () => {
     
        const formData = new FormData();
   
        formData.append(
          "file",
          this.state.selectedFile,
          this.state.selectedFile.name
        );
          console.log(formData);

        console.log(this.state.selectedFile);
      
        axios.post("/izvodac/"+this.props.match.params.id+"/updatePhoto", formData);
        window.location.reload(false);
      };
      deleteItem(e){
        e.preventDefault();
        const {izvodac}=this.state;
        izvodacService.obrisiIzvodaca(izvodac.idIzvodac).then(res=>res.json())
        .then(res=>{
          history.push('/listaIzvodaca');
        });
       
      }
    render(){
        let user = JSON.parse(localStorage.getItem('user'));
        const{izvodac,grupa,glazbenik,ploce} = this.state;
    
        const popisPloca = ploce.length ? (
          <table>
          <thead>
            <tr>
                <th>Godina izdanja</th>
                <th>Naziv ploče</th>
            </tr>
          </thead>
          <tbody>
          { ploce.map(ploca=>{
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

        const jegrupa = izvodac.jegrupa ? (
            <div>
            <h6>Početak djelovanja : {grupa.pocDjelovanjaGrupa}</h6>
            <h6>Kraj djelovanja : {grupa.krajDjelovanjaGrupa}</h6>
            </div>   
            ) : (<div>
                <h6>Datum rođenja: {glazbenik.datumrod}</h6>
                <h6>Datum smrti : {glazbenik.datumSmrti}</h6>
                </div>
            )
        const {slika} = this.state ? (this.state.slika): false
        const prikazSlike = slika ? (
            <div className="slika">
              <img src ={`data:image/jpeg;base64,${slika}`} />
            </div>):
      (<div>Nema slike</div>)

        var isAdmin=false;
        const isLoggedin = user ? true:false;

        if(isLoggedin){
           isAdmin = (user.isAdmin) ? (
                <div>
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
        var obrisiIzvodaca;
        if(isLoggedin){
          obrisiIzvodaca = user.isAdmin ?(
            <button  class="btn waves-effect waves-light " onClick={(e) => { if (window.confirm('Jeste li sigurni da želite obrisati ovog izvođača?')) this.deleteItem(e) } }>
            Obriši
          </button>
          ):(<div></div>)
        }
        return (
            <div className="container section project-details">
              <div className="card grey darken-3">
                <div className="card-content">
                  <div className="row">
                    <div class="col l3">
                      {prikazSlike}
                    </div>
                    <div class="col l6">
                      <div className="card-content" key= {izvodac.idIzvodac}>
                        <span className="card-title">{izvodac.nazivIzvodac} </span>
                        {jegrupa}
                        <br/>
                      </div>
                    </div>
                    <div className="col l3">
                      {obrisiIzvodaca}
                    </div>
                  </div>
                  <div className="row"> 
                      <p>{izvodac.infoIzvodac}</p>
                      {isAdmin}
                  </div>
                  <div className="row">
                    <h6>Diskografija: </h6>
                       {popisPloca}
                  </div>
               </div>
              </div>
            </div>
           
          )
  }
}

export default izvodac;