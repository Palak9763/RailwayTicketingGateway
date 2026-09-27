const bookingForm = document.getElementById('bookingForm');
const statusText = document.getElementById('statusText');
const pnrText = document.getElementById('pnrText');
const txnText = document.getElementById('txnText');
const trainText = document.getElementById('trainText');
const routeText = document.getElementById('routeText');
const classText = document.getElementById('classText');
const statusMessage = document.getElementById('statusMessage');
const submitButton = bookingForm.querySelector('button[type="submit"]');

const todayPlus = () => {
  const date = new Date();
  date.setDate(date.getDate() + 10);
  return date.toISOString().split('T')[0];
};

const updateConfirmation = (details) => {
  statusText.textContent = details.status;
  pnrText.textContent = details.pnr;
  txnText.textContent = details.transactionId;
  trainText.textContent = details.train;
  routeText.textContent = `${details.from} → ${details.to}`;
  classText.textContent = details.travelClass;
};

const simulateBooking = () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve({
        status: 'CONFIRMED',
        pnr: '4512789632',
        transactionId: 'TXN987654321',
        train: '12952',
        from: 'NDLS',
        to: 'MMCT',
        travelClass: '3A'
      });
    }, 1200);
  });
};

document.getElementById('journeyDate').value = todayPlus();

bookingForm.addEventListener('submit', async function (event) {
  event.preventDefault();

  const trainNumber = document.getElementById('trainNumber').value.trim();
  const fromStation = document.getElementById('fromStation').value.trim();
  const toStation = document.getElementById('toStation').value.trim();
  const travelClass = document.getElementById('travelClass').value;

  submitButton.disabled = true;
  submitButton.textContent = 'Sending booking request...';

  const requestDetails = {
    train: trainNumber || '12952',
    from: fromStation || 'NDLS',
    to: toStation || 'MMCT',
    travelClass: travelClass || '3A'
  };

  try {
    statusMessage.textContent = 'Request Processed Successfully';
    const result = await simulateBooking();
    updateConfirmation({
      ...result,
      train: requestDetails.train,
      from: requestDetails.from,
      to: requestDetails.to,
      travelClass: requestDetails.travelClass
    });
  } finally {
    submitButton.disabled = false;
    submitButton.textContent = 'Book Ticket';
  }
});
