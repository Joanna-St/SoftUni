function loadRepos() {
   fetch('https://api.github.com/users/testnakov/repos')
   .then((res) => res.text())
   .then((data) => document.getElementById('res').innerText = data)
   .catch((err) => console.error(err))
}