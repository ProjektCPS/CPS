function getParamFromUrl(paramName) {
    let url_string = window.location.href;
    let url = new URL(url_string);
    return url.searchParams.get(paramName);
}

function reloadPage() {
    return window.location.reload()
}

function goBackAndReloadPage(){
    window.location.href = document.referrer;
}
