import { authHeader } from '../helpers/authHeader';

class izvodacService{

getIzvodaci(){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/izvodac/list', requestOptions);
}  

getIzvodacById(id){
        const requestOptions ={
            method: 'GET',
            headers:{'Content-Type': 'application/json'}
        };
        console.log(id)
        return fetch('/izvodac/'+id, requestOptions);
}

getGlazbenikByIdIzvodac(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/glazbenik/'+id, requestOptions);
}

getGrupaByIdIzvodac(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/grupa/'+id, requestOptions);
}

getIzvodacImg(id){
    const requserOptions = {
        method:'GET'
    }
    return fetch('/izvodac/slika/'+id,requserOptions);
 }

 getIzvodacPloce(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/vinylPloca/izvodacDiskografija/'+id, requestOptions);
 }
 getDrzave(id){
    const requestOptions ={
        method: 'GET',
        headers:{'Content-Type': 'application/json'}
    };
    return fetch('/drzava/list', requestOptions);
 }
 dodajIzvodaca(nazivIzvodac,infoIzvodac,idDrzava,pocDjelovanjaGrupa,krajDjelovanjaGrupa,datumRod,datumSmrti,jeGrupa){
    const requestOptions = {
        method: 'POST',
        headers: authHeader() ,
        body: JSON.stringify({nazivIzvodac,infoIzvodac,idDrzava,pocDjelovanjaGrupa,krajDjelovanjaGrupa,datumRod,datumSmrti,jeGrupa })
    };

    return fetch('/izvodac/dodajIzvodaca', requestOptions);
 }
 obrisiIzvodaca(id){
    const requestOptions={
        method:'DELETE',
        headers:authHeader()
    };
    return fetch('/izvodac/'+id+'/obrisiIzvodaca',requestOptions);
}
searchIzvodaci(filter){
    const requestOptions={
        method:'GET',
    }
    if(filter.trim()==''){
        return  fetch('izvodac/list',requestOptions);
    }else{
        return fetch('izvodac/filter/'+filter.trim(),requestOptions);
    }
    
}
}

export default new izvodacService();
