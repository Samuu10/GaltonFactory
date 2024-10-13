d3.json("movimientos.json").then(data => {
    const svg = d3.select("svg");
    const padding = 20;
    const width = +svg.attr("width") - 2 * padding;
    const height = +svg.attr("height") - 2 * padding;

    // Draw the inverted pyramid with vertical bars
    const niveles = 10;
    const nivelHeight = height / niveles;

    // Draw the final bar at the top center
    svg.append("line")
        .attr("x1", width / 2 + padding)
        .attr("y1", padding)
        .attr("x2", width / 2 + padding)
        .attr("y2", padding + nivelHeight)
        .attr("stroke", "black");

    for (let i = 0; i < niveles; i++) {
        const levelWidth = width * (i + 1) / niveles;
        const xOffset = (width - levelWidth) / 2 + padding;

        // Draw vertical bars
        for (let j = 0; j <= i; j++) {
            const barX = xOffset + (j * levelWidth / i);
            svg.append("line")
                .attr("x1", barX)
                .attr("y1", padding + i * nivelHeight)
                .attr("x2", barX)
                .attr("y2", padding + (i + 1) * nivelHeight)
                .attr("stroke", "black");
        }
    }

    // Group the data by ballId
    const ballsData = d3.group(data, d => d.ballId);

    // Function to animate each ball
    function animateBall(ballData, delay) {
        const ball = fallingBallsSvg.append("circle")
            .attr("class", "bola")
            .attr("cx", width / 2 + padding) // Start at the top center
            .attr("cy", padding)
            .attr("r", 5);

        ballData.forEach((d, index) => {
            const levelWidth = width * (d.nivel + 1) / niveles;
            const xOffset = (width - levelWidth) / 2 + padding;
            const cx = xOffset + d.posicionX * levelWidth;
            const cy = padding + d.nivel * nivelHeight;

            ball.transition()
                .delay(delay + index * 500) // Delay each step
                .duration(500) // Duration of each step
                .attr("cx", cx)
                .attr("cy", cy);
        });
    }

    // Start the animation for each ball with a delay of 0.2 seconds between each ball
    let delay = 0;
    ballsData.forEach(ballData => {
        animateBall(ballData, delay);
        delay += 200; // Increase delay by 0.2 seconds for each ball
    });
});
// Falling balls SVG
const fallingBallsSvg = d3.select("#fallingBalls");

// Histogram SVG
const histogramSvg = d3.select("#histogram");

// Load cuadrantes data and draw histogram
d3.json("cuadrantes.json").then(cuadrantes => {
    const padding = 20;
    const width = +histogramSvg.attr("width") - 2 * padding;
    const height = +histogramSvg.attr("height") - 2 * padding;

    const xScale = d3.scaleLinear()
        .domain([0, cuadrantes.length - 1])
        .range([padding, width + padding]);

    const yScale = d3.scaleLinear()
        .domain([0, d3.max(cuadrantes)])
        .range([height + padding, padding]);

    const barWidth = (width / cuadrantes.length) - 2;

    histogramSvg.selectAll(".bar")
        .data(cuadrantes)
        .enter()
        .append("rect")
        .attr("class", "bar")
        .attr("x", (d, i) => xScale(i))
        .attr("y", d => yScale(d))
        .attr("width", barWidth)
        .attr("height", d => height + padding - yScale(d))
        .attr("fill", "steelblue");

    // X Axis
    const xAxis = d3.axisBottom(xScale).ticks(cuadrantes.length);
    histogramSvg.append("g")
        .attr("transform", `translate(0, ${height + padding})`)
        .call(xAxis);

    // Y Axis
    const yAxis = d3.axisLeft(yScale);
    histogramSvg.append("g")
        .attr("transform", `translate(${padding}, 0)`)
        .call(yAxis);
});