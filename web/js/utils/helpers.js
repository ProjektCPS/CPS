function getParamFromUrl(paramName) {
    let url_string = window.location.href;
    let url = new URL(url_string);
    return url.searchParams.get(paramName);
}
