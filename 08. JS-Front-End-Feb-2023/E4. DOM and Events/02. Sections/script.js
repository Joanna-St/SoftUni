function create(words) {

   for (const word of words) {
      let container = document.getElementById('content');
      let newDiv = document.createElement('div');
      let newPar = document.createElement('p');
      
      newPar.innerText = word;
      newPar.style.display = 'none';

      newDiv.appendChild(newPar);
      newDiv.addEventListener('click', unhide)

      container.appendChild(newDiv)

      function unhide(e){
         this.firstChild.style.display = 'block'
      }
   }
}