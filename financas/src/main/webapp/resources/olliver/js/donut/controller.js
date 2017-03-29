function colors(saldo, maior) {
    var color = "#28bf50";
    if (saldo >= (maior * 0.8)) {
        color = "#ff0000";
    } else if (saldo < (maior * 0.8) && saldo > (maior * 0.4)) {
        color = "#ffaa00";
    }
    return color;
}