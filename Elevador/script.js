let currentFloorElevator1 = 2; // Térreo
let currentFloorElevator2 = 2; // Térreo

function moveElevator(elevator, targetFloor) {
    const elevatorElement = document.getElementById(elevator);
    elevatorElement.innerText = `Elevador ${elevator.charAt(elevator.length - 1)} - Andar ${targetFloor}`;
    elevatorElement.classList.add('elevator-arrived');

    setTimeout(() => {
        elevatorElement.classList.remove('elevator-arrived');
    }, 500);
}

function requestElevator(targetFloor) {
    const distanceElevator1 = Math.abs(targetFloor - currentFloorElevator1);
    const distanceElevator2 = Math.abs(targetFloor - currentFloorElevator2);

    if (distanceElevator1 <= distanceElevator2) {
        moveElevator("elevator1", targetFloor);
        currentFloorElevator1 = targetFloor;
    } else {
        moveElevator("elevator2", targetFloor);
        currentFloorElevator2 = targetFloor;
    }

    const buttonElement = document.getElementById(`button-${targetFloor}`);
    buttonElement.classList.add('button-pressed');

    setTimeout(() => {
        buttonElement.classList.remove('button-pressed');
    }, 500);
}