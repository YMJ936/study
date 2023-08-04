// 상품 개당 가격 설정
let productPrice1 = 9900; // 첫 번째 상품 개당 가격
let productPrice2 = 9900; // 두 번째 상품 개당 가격
const shippingFee = 3000; // 배송비
const freeShippingThreshold = 100000; // 10만원 이상 무료 배송 기준

// 수량 변경에 따른 금액 업데이트
function updateQuantity(change, productId) {
  let quantityInput;
  let priceElement;

  if (productId === 1) {
    quantityInput = document.getElementById("p_cntInput1");
    priceElement = document.getElementById("p_sum1");
  } else if (productId === 2) {
    quantityInput = document.getElementById("p_cntInput2");
    priceElement = document.getElementById("p_sum2");
  } else {
    return; // 잘못된 값이 전달되면 함수 종료
  }

  let quantityValue = parseInt(quantityInput.value);

  // 수량 증가 또는 감소
  quantityValue += change;

  // 최소 수량은 1로 설정
  if (quantityValue < 1) {
    quantityValue = 1;
  }

  // 변경된 수량 값을 수량 입력 필드에 업데이트
  quantityInput.value = quantityValue;

  // 금액 업데이트
  updatePrice(productId);
  updateTotalPrice();
}

// 금액 업데이트 함수
function updatePrice(productId) {
  let quantityInput;
  let priceElement;

  if (productId === 1) {
    quantityInput = document.getElementById("p_cntInput1");
    priceElement = document.getElementById("p_sum1");
  } else if (productId === 2) {
    quantityInput = document.getElementById("p_cntInput2");
    priceElement = document.getElementById("p_sum2");
  } else {
    return; // 잘못된 값이 전달되면 함수 종료
  }

  let quantityValue = parseInt(quantityInput.value);

  // 개당 가격 설정
  let unitPrice = productId === 1 ? productPrice1 : productPrice2;

  // 수량에 따른 총 금액 계산
  let totalPrice = unitPrice * quantityValue;

  // 금액 표시 업데이트
  priceElement.textContent = totalPrice.toLocaleString() + "원";
}


// 총 구매금액 업데이트 함수
function updateTotalPrice() {
  // 상품 1과 상품 2의 수량과 개당 가격 가져오기
  let quantityInput1 = document.getElementById("p_cntInput1");
  let quantityInput2 = document.getElementById("p_cntInput2");
  let quantityValue1 = parseInt(quantityInput1.value);
  let quantityValue2 = parseInt(quantityInput2.value);

  // 상품 1과 상품 2의 가격 계산
  let totalPrice1 = productPrice1 * quantityValue1;
  let totalPrice2 = productPrice2 * quantityValue2;

  // 상품 1과 상품 2의 가격을 더해서 총 구매금액 계산
  let totalPurchaseAmount = totalPrice1 + totalPrice2;

  // 10만원 이상 구매 시 배송비 제외
  let shippingFeeIncluded = true;
  if (totalPurchaseAmount >= freeShippingThreshold) {
    shippingFeeIncluded = false;
  }

  // 배송비를 총 구매금액에 추가
  totalPurchaseAmount += shippingFeeIncluded ? shippingFee : 0;

  // totalprice 요소 업데이트
  let totalpriceElement = document.getElementById("totalprice");

  if (totalPurchaseAmount > 0) {
    totalpriceElement.textContent = totalPurchaseAmount.toLocaleString() + "원";
  } else {
    totalpriceElement.textContent = "장바구니에 담긴 상품이 없습니다.";
  }
}

function deleteRow(productId) {
  const row = document.getElementById("cart" + productId);
  const priceElement = row.querySelector(".price");
  const priceValue = parseInt(priceElement.textContent.replace(/[^0-9]/g, ""));
  const quantityInput = document.getElementById("p_cntInput" + productId);
  const quantityValue = parseInt(quantityInput.value);
  const totalPriceElement = document.getElementById("totalprice");
  let totalPrice = parseInt(totalPriceElement.textContent.replace(/[^0-9]/g, ""));

 // 차감 금액이 0 이상인 경우에만 차감
  if (priceValue > 0) {
    const subtractedPrice = priceValue * quantityValue;
    totalPrice -= subtractedPrice;

    // 상품 1과 상품 2의 누계 가격에서 차감
    if (productId === 1) {
      productPrice1 -= subtractedPrice;
    } else if (productId === 2) {
      productPrice2 -= subtractedPrice;
    }
  }
  
   // 상품 삭제 시 해당 상품의 개수와 가격을 0으로 초기화
  quantityInput.value = 0;
  priceElement.textContent = "0원";

  // 전체 금액이 0 이하인 경우 0으로 설정
  if (totalPrice < 0) {
    totalPrice = 0;
  }

  totalPriceElement.textContent = totalPrice.toLocaleString() + "원";


  // 총 구매금액 업데이트
  updateTotalPrice();

  row.remove();
}


// 페이지 로드 시 초기 금액 업데이트
updatePrice(1);
updatePrice(2);
updateTotalPrice();