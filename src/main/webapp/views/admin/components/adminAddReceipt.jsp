
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
									method="POST" enctype="multipart/form-data">

										<div class="col-md-12">
										<input class="form-control" type="hidden" name="announceId"
												placeholder="Full Name" required id="addAnnId">
											<input class="form-control" type="text" name="receiptNo"
												placeholder="receipt No" required id="receiptNoE">

										</div>

										<div class="col-md-12">
											<input class="form-control" type="date" name="Date"
												placeholder="receiptDate" required id="receiptDateE">

										</div>
										<div class="col-md-12">
											<input class="form-control" type="number" name="amount"
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
</script>