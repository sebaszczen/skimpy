var haslo="Ala ma Kota";
haslo=haslo.toUpperCase();
var dlugosc=haslo.length;
var hasloZakryte="";

for(i =0; i<dlugosc; i++){
if(haslo.charAt(i)==" "){
hasloZakryte=hasloZakryte+" ";
}
else{
hasloZakryte=hasloZakryte+"-";
}
}

function odswiez_haslo(){
document.getElementById("head").innerHTML=hasloZakryte;
}

window.onload=start;


  var litery = new Array(35);
  litery[0] = "A";
  litery[1] = "Ą";
  litery[2] = "B";
  litery[3] = "C";
  litery[4] = "Ć";
  litery[5] = "D";
  litery[6] = "E";
  litery[7] = "Ę";
  litery[8] = "F";
  litery[9] = "G";
  litery[10] = "H";
  litery[11] = "I";
  litery[12] = "J";
  litery[13] = "K";
  litery[14] = "L";
  litery[15] = "Ł";
  litery[16] = "M";
  litery[17] = "N";
  litery[18] = "Ń";
  litery[19] = "O";
  litery[20] = "Ó";
  litery[21] = "P";
  litery[22] = "Q";
  litery[23] = "R";
  litery[24] = "S";
  litery[25] = "Ś";
  litery[26] = "T";
  litery[27] = "U";
  litery[28] = "V";
  litery[29] = "W";
  litery[30] = "X";
  litery[31] = "Y";
  litery[32] = "Z";
  litery[33] = "Ż";
  litery[34] = "Ź";


function start(){
var tresc_diva="";
for(i=0; i<35;i++){

tresc_diva=tresc_diva+'<div class="letter" onclick="sprawdz('+i+')" id="lit'+i+'">'+litery[i]+'</div>'
if((i+1)%7==0){
tresc_diva=tresc_diva+'<div style="clear:both;"></div>'
}
}
document.getElementById("right").innerHTML=tresc_diva;

odswiez_haslo();
}
var pomylki=0;

function sprawdz(j){
var licz=0;
var trafiona=false;
for(a =0; a<dlugosc; a++){
if(haslo.charAt(a)==litery[j]){
trafiona=true;
hasloZakryte=hasloZakryte.ustawZnak(a,litery[j])
}
}
var elemet="lit"+j;
if(trafiona==false){
pomylki=pomylki+1;
document.getElementById("left").innerHTML='<img src="/images/s'+pomylki+'.jpg"/>'
document.getElementById(elemet).style.background="#330000";
document.getElementById(elemet).style.color="#330000";
document.getElementById(elemet).style.border="#330000";
document.getElementById(elemet).style.cursor="default";
document.getElementById(elemet).setAttribute("onclick",";");
}
else{
odswiez_haslo();
document.getElementById(elemet).style.background="#003300";
document.getElementById(elemet).style.color="#00C000";
document.getElementById(elemet).style.border="#00C000";
document.getElementById(elemet).style.cursor="default";
}

if(hasloZakryte==haslo){
document.getElementById("right").innerHTML='<h1>Gratulacje wygrałeś!</h1>'
}
if(pomylki>7){
document.getElementById("right").innerHTML='<h1 style="{color:red; cursor:pointer}" onclick="location.reload()" >Game over!</h1>'
}

}

String.prototype.ustawZnak= function(miejsce,znak){
if(miejsce>dlugosc-1){
return this;
}
else{
return this.substr(0,miejsce)+znak+this.substr(miejsce+1);
}
}

