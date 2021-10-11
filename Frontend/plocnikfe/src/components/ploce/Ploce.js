import React, { Component } from 'react'
import axios from 'axios';
import "../css/Card.css";
import "../css/Sum.css";
import "../css/ploce.css"
import PlocaSum from './plocaSum';
import plocaService from '../services/plocaService';
import { data } from 'jquery';

class Ploce extends Component {
  constructor(props){
    super(props);
    this.state = {
      ploce:[],
      ploceR:[],
      sort:'abecedno',
      vrsta:'',
      aCheck:true,
      cUCheck:false,
      cSCheck:false,
      sveCheck:true,
      sveZanr:true,
      zanr:"svi",
      vrsta:"sve",
      svaRazdoblja:true,
      razdoblje:"sva",
      filter:''
    }
    this.handleSort = this.handleSort.bind(this);
    this.handleVrsta = this.handleVrsta.bind(this);
    this.handleZanr = this.handleZanr.bind(this);
    this.handleFilter = this.handleFilter.bind(this);
    this.handleRazdoblje = this.handleRazdoblje.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.handleSearch = this.handleSearch.bind(this);
  }
  
  componentDidMount(){
    axios.all([axios.get('/vinylPloca/list')])
        .then(axios.spread((ploceRes) =>{
            this.setState({
               ploce:ploceRes.data,
               ploceR:ploceRes.data
            }) 
        }));
  }

  handleSort(e){
    console.log(e.target)
    switch(e.target.value){
      case "abecedno":
        console.log("sort abecedno")
        this.setState({
          ploce: this.state.ploce.sort((a,b) => (a.nazivVinylPloca > b.nazivVinylPloca) ? 1 : ((b.nazivVinylPloca > a.nazivVinylPloca) ? -1 : 0)),
          cUCheck:false,
          cSCheck:false,
          aCheck:true
        })
        console.log(this.state.ploce);
        break;
      case "ocjena(silazno)":
        console.log("sort by ocjena")
        this.setState({
          ploce: this.state.ploce.sort(function(a,b){
            return parseFloat(b.ukOcjena) - parseFloat(a.ukOcjena);
          }),
          cUCheck:false,
          cSCheck:true,
          aCheck:false
        })
        break;
      case "ocjena(uzlazno)":
        console.log("sort by ocjena")
        this.setState({
           ploce: this.state.ploce.sort(function(a,b){
              return parseFloat(a.ukOcjena) - parseFloat(b.ukOcjena);
            }),
            cUCheck:true,
            cSCheck:false,
            aCheck:false
        })
      break;
      default:
        break;
    }
  }

  handleZanr(e){
    console.log(e.target)
    if(e.target.value!=="svi"){
      this.setState({
        zanr:e.target.value,
        sveZanr:false
      })
    }else{
      this.setState({
        zanr:e.target.value,
        sveZanr:true
      })
    }

  }
  handleVrsta(e){
    console.log(e.target)

    if(e.target.value!=="sve"){
      this.setState({
        vrsta:e.target.value,
        sveCheck:false
      })
    }else{
      this.setState({
        vrsta:e.target.value,
        sveCheck:true
      })
    }
  }
  handleRazdoblje(e){
    if(e.target.value!=="sva"){
      this.setState({
        razdoblje:e.target.value,
        svaRazdoblja:false
      })
    }else{
      this.setState({
        razdoblje:e.target.value,
        svaRazdoblja:true
      })
    }
  }
  handleFilter(){
    const{zanr,vrsta,ploceR,razdoblje}=this.state;

    if(zanr!=="svi" && vrsta!== "sve" && razdoblje!== "sva"){
      console.log("sve drugacije");
      axios.all([axios.get('/zanr/'+zanr)])
      .then(axios.spread((zanrRes) =>{
            var ploceZanr=[];
            for(var i=0 ;i<zanrRes.data.length;i++){
              ploceZanr[i]=zanrRes.data[i].idVinylPloca;
            }  
            var ploceVrsta=[];
            var b=0;
            for(var i=0; i<ploceR.length ;i++){
            if(ploceR[i].idVrsta.nazivVrsta===vrsta){
              ploceVrsta[b]=ploceR[i];
              b++;
             }
            }
            var razdobljeP=[]
            var k=0;
            var r=parseInt(razdoblje);
            for(var i=0;i<ploceVrsta.length;i++){
              if(ploceVrsta[i].godinaIzdanja>=r && ploceVrsta[i].godinaIzdanja<=(r+9)){
                razdobljeP[k]=ploceVrsta[i];
                k++
              }
            }
            var nove = razdobljeP.filter(a => ploceZanr.some(b => a.idVinylPloca === b.idVinylPloca));
            console.log(nove)
          this.setState({
            ploce:nove
          })
      }));


    }else if(zanr!=="svi" && vrsta!=="sve"){
      console.log("zanr drugaciji vrsta sve");

      axios.all([axios.get('/zanr/'+zanr)])
      .then(axios.spread((zanrRes) =>{
            var ploceZanr=[];
            for(var i=0 ;i<zanrRes.data.length;i++){
              ploceZanr[i]=zanrRes.data[i].idVinylPloca;
            }
            var ploceVrsta=[];
            var b=0;
            for(var i=0; i<ploceR.length ;i++){
            if(ploceR[i].idVrsta.nazivVrsta===vrsta){
              ploceVrsta[b]=ploceR[i];
              b++;
             }
            }
          var nove = ploceVrsta.filter(a => ploceZanr.some(b => a.idVinylPloca === b.idVinylPloca));
          this.setState({
            ploce:nove
          })
      }));
    }else if(zanr!=="svi" && razdoblje!=="sva"){
      axios.all([axios.get('/zanr/'+zanr)])
      .then(axios.spread((zanrRes) =>{
            var ploceZanr=[];
            for(var i=0 ;i<zanrRes.data.length;i++){
              ploceZanr[i]=zanrRes.data[i].idVinylPloca;
            }
            var razdobljeP=[]
            var k=0;
            var r=parseInt(razdoblje);
            for(var i=0;i<ploceZanr.length;i++){
              if(ploceZanr[i].godinaIzdanja>=r && ploceZanr[i].godinaIzdanja<=(r+9)){
                razdobljeP[k]=ploceZanr[i];
                k++
              }
            }
          
          this.setState({
            ploce:razdobljeP
          })
      }));
    }else if(vrsta!=="sve" && razdoblje!=="sva"){
      var ploceVrsta=[];
      var b=0;
      for(var i=0; i<ploceR.length ;i++){
      if(ploceR[i].idVrsta.nazivVrsta===vrsta){
        ploceVrsta[b]=ploceR[i];
        b++;
       }
      }
      var razdobljeP=[]
      var k=0;
      var r=parseInt(razdoblje);
      for(var i=0;i<ploceVrsta.length;i++){
        if(ploceVrsta[i].godinaIzdanja>=r && ploceVrsta[i].godinaIzdanja<=(r+9)){
          razdobljeP[k]=ploceVrsta[i];
          k++
        }
      }
      this.setState({
        ploce:razdobljeP
      })
    }else if(razdoblje!=="sva"){
      var razdobljeP=[]
      var k=0;
      var r=parseInt(razdoblje);
      for(var i=0;i<ploceR.length;i++){
        if(ploceR[i].godinaIzdanja>=r && ploceR[i].godinaIzdanja<=(r+9)){
          razdobljeP[k]=ploceR[i];
          k++
        }
      }  
      this.setState({
        ploce:razdobljeP
      })
    }else if(zanr!=="svi"){
      axios.all([axios.get('/zanr/'+zanr)])
      .then(axios.spread((zanrRes) =>{
            var ploceZanr=[];
            for(var i=0 ;i<zanrRes.data.length;i++){
              ploceZanr[i]=zanrRes.data[i].idVinylPloca;
            }
          this.setState({
            ploce:ploceZanr
          })
      }));

    }
    else if(vrsta!=="sve"){
      console.log("vrsta razlicita zanr svi");
      var ploceVrsta=[];
      var b=0;
      for(var i=0; i<ploceR.length ;i++){
       if(ploceR[i].idVrsta.nazivVrsta===vrsta){
         ploceVrsta[b]=ploceR[i];
         b++;
       }
      }
      this.setState({
        ploce:ploceVrsta
      })
    }else{
      console.log(" sve");
      this.setState({
        ploce:ploceR
      })
    }

  }
  handleChange(e){
    const { name, value } = e.target;
     this.setState({ [name]: value });
  }
  handleSearch(e){
    e.preventDefault();
    const {filter} =this.state;
    plocaService.searchPloce(filter).then(res=>res.json())
    .then(res=>{
      this.setState({
        ploce:res
      })
    });
  }

  render() {
    const {ploce} = this.state;
    const ploceList = ploce.length ? (
      ploce.map(ploca => {
        return(
          <PlocaSum ploca={ploca} plocaProfil={false} key={ploca.idVinylPloca}/>
        )
      })
    ):(
      <div>Nema ploča</div>
    )
    return(
      <div className="container">
        <div className="box">
          <div className="component">
            <div className="row row-filter">
              <div className="filter col s6 search">
               
              <div className="col s1">
                <i className="material-icons search pinkIcon">search</i>
                <i class="material-icons search blueIcon">search</i>
              </div>
              <div className="col s8">
                <input placeholder="Pretraži po nazivu ploče" id="first_name" name="filter" value={this.state.filter} onChange={this.handleChange} type="text" />
              </div>
              <div>
                <button class="btn waves-effect waves-light" onClick={this.handleSearch}>
                  Pretraži
                </button>
              </div>
              </div>
            </div>
            <div className ="row row-filter">
            <div className="filter col s2">
                <h5>Sortiraj:</h5>
                <form action="#">
                    <p>
                      <label className="label-sadrzaj">
                        <input name="group1" type="radio" name="sort" value="abecedno" onChange={this.handleSort} checked={this.state.aCheck} />
                        <span>Naziv ploča</span>
                      </label>
                    </p>
                    <p>
                      <label className="label-sadrzaj"> 
                        <input name="group1" type="radio" name="sort" value="ocjena(silazno)" onChange={this.handleSort} checked={this.state.cSCheck}/>
                        <span>Ocjena (silazno)</span>
                      </label>
                    </p>
                    <p>
                      <label className="label-sadrzaj">
                      <input name="group1" type="radio" name="sort" value="ocjena(uzlazno)" onChange={this.handleSort} checked={this.state.cUCheck}/>
                        <span>Ocjena (uzlazno)</span>
                      </label>
                    </p>
                  </form>
              </div>

              <div className="filter col s10">
               
                <div className="filter col s5">
                  <h6>Žanr:</h6>
                  <form action="#">
                    <div className="col">
                       <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="svi" onChange={this.handleZanr}  checked={this.state.sveZanr}/>
                            <span>Svi</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="Rock" onChange={this.handleZanr}  />
                            <span>Rock</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj"> 
                            <input name="group1" type="radio" name="zanr" value="Alternativni rock" onChange={this.handleZanr} />
                            <span>Alternativni rock</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="Zabavna glazba" onChange={this.handleZanr} />
                            <span>Zabavna glazba</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="Pop rock" onChange={this.handleZanr} />
                            <span>Pop rock</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="Progresivni rock" onChange={this.handleZanr} />
                            <span>Progresivni rock</span>
                          </label>
                        </p>
                    </div>
                    <div className="col s3">
                       <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="Punk" onChange={this.handleZanr} />
                            <span>Punk</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="Electronic" onChange={this.handleZanr}  />
                            <span>Electronic</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj"> 
                            <input name="group1" type="radio" name="zanr" value="Pop" onChange={this.handleZanr} />
                            <span>Pop</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="R&B" onChange={this.handleZanr} />
                            <span>R&B</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="Jazz" onChange={this.handleZanr} />
                            <span>Jazz</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="Disco" onChange={this.handleZanr} />
                            <span>Disco</span>
                          </label>
                        </p>
                    </div>
                  </form>
                </div>
                <div className="filter col s3">
                  <h6>Razdoblje:</h6>
                  <form action="#">
                    <div className="col">
                       <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="sva" onChange={this.handleRazdoblje}  checked={this.state.svaRazdoblja}/>
                            <span>Sva</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="zanr" value="1960" onChange={this.handleRazdoblje}  />
                            <span>60te</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj"> 
                            <input name="group1" type="radio" name="zanr" value="1970" onChange={this.handleRazdoblje} />
                            <span>70te</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="1980" onChange={this.handleRazdoblje} />
                            <span>80te</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="1990" onChange={this.handleRazdoblje} />
                            <span>90te</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="zanr" value="2000" onChange={this.handleRazdoblje} />
                            <span>00te</span>
                          </label>
                        </p>
                    </div>
                  </form>
                </div>
                <div className="filter col s3">
                  <h6>Vrsta ploče:</h6>
                  <form action="#">
                        <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="vrsta" value="sve" onChange={this.handleVrsta} checked={this.state.sveCheck} />
                            <span>Sve</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                            <input name="group1" type="radio" name="vrsta" value="Long play" onChange={this.handleVrsta}  />
                            <span>Long play (LP)</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj"> 
                            <input name="group1" type="radio" name="vrsta" value="Single" onChange={this.handleVrsta} />
                            <span>Single</span>
                          </label>
                        </p>
                        <p>
                          <label className="label-sadrzaj">
                          <input name="group1" type="radio" name="vrsta" value="Extended play" onChange={this.handleVrsta} />
                            <span>Extended play (EP)</span>
                          </label>
                        </p>
                  </form>
                </div>
                <div className="row button-search">
                <button class="btn waves-effect waves-light" onClick={this.handleFilter} >Filtriraj</button>
                </div>
              </div>
            </div>
          </div>
        </div>
     
        <div class="box container-card">
          {ploceList}
        </div>    

      </div>
     
    )
  }  
}

export default Ploce;