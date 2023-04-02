function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {
      let searchField = document.getElementById('searchField');
      let searchQuery = searchField.value;
      let allRows = Array.from(document.querySelectorAll('tbody > tr'));

      for (const row of allRows) {
         row.className = '';
      }

      searchField.value = null;

      if (searchQuery.trim().length > 0) {
         for (const row of allRows) {
            let allCells = Array.from(row.children);

            for (const cell of allCells) {
               let cellContent = cell.textContent.toLowerCase();

               if (cellContent.includes(searchQuery.toLowerCase())) {
                  row.className = 'select'
                  break;
               }
            }
         }
      }
   }
}