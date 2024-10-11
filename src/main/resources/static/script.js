d3.json("movimientos.json").then(function(data) {
    console.log("Datos cargados:", data); // Mensaje de depuración

    const svg = d3.select("svg");

    // Configurar escala para el gráfico
    const xScale = d3.scaleLinear().domain([0, 500]).range([0, 600]);
    const yScale = d3.scaleLinear().domain([0, 500]).range([0, 600]);

    // Dibujar el tablero
    svg.append("rect")
        .attr("width", 600)
        .attr("height", 600)
        .attr("fill", "lightgrey");

    // Agrupar posiciones por bolas
    const bolas = [];
    let currentBola = [];
    data.forEach((posicion, index) => {
        currentBola.push(posicion);
        if ((index + 1) % 10 === 0) { // Assuming each ball has 10 positions
            bolas.push({ posiciones: currentBola });
            currentBola = [];
        }
    });

    // Itera sobre las bolas
    bolas.forEach(bola => {
        console.log("Procesando bola:", bola); // Mensaje de depuración

        const line = d3.line()
            .x(d => xScale(d.posicionX))
            .y(d => yScale(d.posicionY));

        // Dibuja las líneas que representan los movimientos
        svg.append("path")
            .datum(bola.posiciones)
            .attr("d", line)
            .attr("stroke", "black")
            .attr("fill", "none");

        // Dibuja las bolas en el gráfico
        svg.selectAll("circle")
            .data(bola.posiciones)
            .enter()
            .append("circle")
            .attr("class", "bola")
            .attr("cx", d => xScale(d.posicionX))
            .attr("cy", d => yScale(d.posicionY))
            .attr("r", 5);
    });
}).catch(function(error) {
    console.error("Error al cargar los datos:", error); // Mensaje de depuración
});