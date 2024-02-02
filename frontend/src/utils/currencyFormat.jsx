export const currencyFormat = (number) => {
  return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}