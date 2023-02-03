function recherche() {
    var critere = document.getElementById("critere");
    console.log(critere);
    var xhr = getXhr();

    //Définition des changements d'états    
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var retour = JSON.parse(xhr.responseText);
                console.log(xhr.responseText);
                console.log("retour : " + retour[0].idVehicule);
                let recherche = document.getElementById("recherche");

                recherche.innerHTML = "";
                for (var i = 0; i < retour.length; i++) {
                    recherche.innerHTML += "<tr>";

                    recherche.innerHTML += "<td>" + retour[i].idEnchere+ "</td>";
                    recherche.innerHTML += "<td>" + retour[i].description + "</>";
                    recherche.innerHTML += "<td>" + retour[i].duree + "</td>";
                    recherche.innerHTML += "<td>" + retour[i].datedebut + "</td>";
                    recherche.innerHTML += "<td>" + retour[i].prixmin + "</td>";
                    recherche.innerHTML += "<td>" + retour[i].photoenchereid + "</td>";
                    recherche.innerHTML += "<td>" + retour[i].statueid + "</td>";
                    recherche.innerHTML += "<td>" + retour[i].produitid + "</td>";
                    recherche.innerHTML += "<td><button onclick=\"/enchere/enchrire?idEnchere="+retour[i].idEnchere+"\">Encherir</button></td>";

                    recherche.innerHTML += "</tr>";
                }

            } else {
                document.dyn = "Error code " + xhr.status;
            }
        }
    };

    let url = "http://localhost:8080/enchere/search?id=" + critere;

    //XMLHttpRequest.open(method, url, async)
    xhr.open("GET", url, true);

    //XMLHttpRequest.send(body)
    xhr.send();
}

function getXhr() {
    //création de l'objet XMLHttpRequest
    var xhr;
    try { xhr = new ActiveXObject('Msxml2.XMLHTTP'); }
    catch (e) {
        try { xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
        catch (e2) {
            try { xhr = new XMLHttpRequest(); }
            catch (e3) { xhr = false; }
        }
    }
    return xhr;
}