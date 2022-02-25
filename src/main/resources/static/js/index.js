const checkBox = document.getElementById("check-box")
const secondMenu = document.getElementById("secondary-menu")
checkBox.addEventListener("click", function() {
    if(checkBox.checked === true) {
        secondMenu.style.transform = "translateX(0%)"
    } else {
        secondMenu.style.transform = "translateX(-110%)"
    }
})
