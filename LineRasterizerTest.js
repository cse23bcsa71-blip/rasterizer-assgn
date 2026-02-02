// Global variables
const GRID_SIZE = 50;
const CELL_SIZE = 10;
const CANVAS_SIZE = GRID_SIZE * CELL_SIZE;
const ENDPOINT_RADIUS = 8;

let endpoint1;
let endpoint2;
let dragging = null;
let rasterizer;

function setup() {
    createCanvas(CANVAS_SIZE, CANVAS_SIZE);
    
    // Initialize endpoints
    endpoint1 = new Point(10, 10);
    endpoint2 = new Point(40, 35);
    
    // Create rasterizer instance
    rasterizer = new BresenhamRasterizer();
}

function draw() {
    background(255);
    
    // Draw grid
    stroke(220);
    strokeWeight(1);
    for (let i = 0; i <= GRID_SIZE; i++) {
        line(i * CELL_SIZE, 0, i * CELL_SIZE, CANVAS_SIZE);
        line(0, i * CELL_SIZE, CANVAS_SIZE, i * CELL_SIZE);
    }
    
    // Get rasterized pixels
    const rasterizedPixels = rasterizer.rasterize(endpoint1, endpoint2);
    
    // Draw rasterized pixels as filled cells
    fill(100, 150, 255, 150);
    noStroke();
    for (let p of rasterizedPixels) {
        rect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
    
    // Draw reference line (1px thin line)
    stroke(255, 0, 0);
    strokeWeight(1);
    line(endpoint1.x * CELL_SIZE + CELL_SIZE/2, 
         endpoint1.y * CELL_SIZE + CELL_SIZE/2,
         endpoint2.x * CELL_SIZE + CELL_SIZE/2, 
         endpoint2.y * CELL_SIZE + CELL_SIZE/2);
    
    // Draw endpoints
    fill(255, 100, 100);
    stroke(200, 50, 50);
    strokeWeight(2);
    ellipse(endpoint1.x * CELL_SIZE + CELL_SIZE/2, 
            endpoint1.y * CELL_SIZE + CELL_SIZE/2, 
            ENDPOINT_RADIUS * 2, ENDPOINT_RADIUS * 2);
    ellipse(endpoint2.x * CELL_SIZE + CELL_SIZE/2, 
            endpoint2.y * CELL_SIZE + CELL_SIZE/2, 
            ENDPOINT_RADIUS * 2, ENDPOINT_RADIUS * 2);
}

function mousePressed() {
    // Check if clicking on endpoint1
    const d1 = dist(mouseX, mouseY, 
                    endpoint1.x * CELL_SIZE + CELL_SIZE/2, 
                    endpoint1.y * CELL_SIZE + CELL_SIZE/2);
    if (d1 < ENDPOINT_RADIUS) {
        dragging = endpoint1;
        return;
    }
    
    // Check if clicking on endpoint2
    const d2 = dist(mouseX, mouseY, 
                    endpoint2.x * CELL_SIZE + CELL_SIZE/2, 
                    endpoint2.y * CELL_SIZE + CELL_SIZE/2);
    if (d2 < ENDPOINT_RADIUS) {
        dragging = endpoint2;
        return;
    }
}

function mouseDragged() {
    if (dragging !== null) {
        // Convert mouse position to grid coordinates
        const gridX = constrain(floor(mouseX / CELL_SIZE), 0, GRID_SIZE - 1);
        const gridY = constrain(floor(mouseY / CELL_SIZE), 0, GRID_SIZE - 1);
        
        dragging.x = gridX;
        dragging.y = gridY;
    }
}

function mouseReleased() {
    dragging = null;
}
