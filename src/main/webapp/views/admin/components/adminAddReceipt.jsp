
<div tabindex="-1" id="addReceiptModal" class="modal hide fade in" style="height=200">
	<div class="modal-dialog">
		<div class="modal-content modal-body ">
			<div class="modal-header ">
				<div class="modal-title d-flex justify-content-center">

					<h5 id="addModaltitle"></h5>
				</div>

				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div
				class="modal-body d-flex justify-content-center bg-success text-white">



				<div class="form-body">
					<div class="row">
						<div class="form-holder">
							<div class="form-content">
								<div>

									<p>Fill in the data below.</p>
									<form action="${pageContext.request.contextPath}/AdminSaveAndUpdateArthikSahyogReceipt" 
									method="POST" enctype="multipart/form-data" name="myForm">

										<div class="col-md-12">
										<input class="form-control" type="hidden" name="announceId"
												  id="addAnnId">
												  
												<!-- <input class="form-control" type="hidden" name="id"
												  id="receiptIdE"> -->
												  
											<input class="form-control mt-3" type="text" name="receiptNo"
												placeholder="Enter Receipt No" required id="receiptNoE" style="font-weight: bold;">


										</div>

										<div class="col-md-12">
											<input class="form-control mt-3" type="date" name="Date"
												placeholder="receiptDate" required id="receiptDateE">

										</div>
										<div class="col-md-12">
											<input class="form-control mt-3" type="number" name="amount"
												placeholder="amount" required id="amountE">

										</div>
										
									
										<div class="form-button mt-3">
											<button  type="submit" class="btn btn-primary">Save
												Receipt</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>
</div>



<script type="text/javascript">
	$(document).ready(function() {
		$('#addReceiptModal').modal({
			backdrop : 'static',
			keyboard : false
		})
		
		
	});
	function addReceipt(annId,name)
	{
		
		$("#receiptNoE").val("");
		$("#receiptDateE").val("");
		$("#amountE").val("");
		
		$("#addAnnId").val(annId);
		$("#addModaltitle").html("Add Receipt- "+name);
		
		$('#addReceiptModal').modal('show');
	}
	function updateReceipt(annId,id,receiptNo,receiptDate,receiptAmount)
	{
		//alert("hello")
		var x = document.createElement("INPUT");
x.setAttribute("type", "hidden");
x.setAttribute("value", id);
x.setAttribute("name", "id");
myForm.appendChild(x);
/* $("#receiptIdE").val(id); */
		$("#addAnnId").val(annId);
		
		$("#receiptNoE").val(receiptNo);
		$("#receiptDateE").val(receiptDate);
		$("#amountE").val(receiptAmount);
		$("#addModaltitle").html("Update Receipt- "+receiptNo);	
		$('#addReceiptModal').modal('show');
	}
</script>