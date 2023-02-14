
<div tabindex="-1" id="addAnnModal" class="modal hide fade in">
	<div class="modal-dialog">
		<div class="modal-content modal-body ">
			<div class="modal-header ">
				<div class="modal-title d-flex justify-content-center">

					<h5 id="modaltitle"></h5>
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
									<form action="${pageContext.request.contextPath}/saveAndUpdateArthikSahyog" 
									method="POST" enctype="multipart/form-data">

										<div class="col-md-12">
										<input class="form-control" type="hidden" name="annId"
												placeholder="Full Name" required id="annId">
											<input class="form-control" type="text" name="name"
												placeholder="Full Name" required id="nameE">

										</div>

										<div class="col-md-12">
											<input class="form-control" type="text" name="address"
												placeholder="Address" required id="addressE">

										</div>
										<div class="col-md-12">
											<input class="form-control" type="text" name="mobile"
												placeholder="Mobile No" required id="mobileE">

										</div>
										<div class="col-md-12">
											<input class="form-control" type="text" name="announceAmount"
												placeholder="announceAmount" required id="annouceE">

										</div>
										<div class="col-md-12  mt-3">
											 <input  type="file" placeholder="Photo" 
											 name="sahyogkrta_photo"
											 
											 >
										</div>
									
										<div class="form-button mt-3">
											<button  type="submit" class="btn btn-primary">Save
												Announcement</button>
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

<div class="text-center">
	<button onclick="addAnnoucement()">Add
		Announcement</button>


</div>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addAnnModal').modal({
			backdrop : 'static',
			keyboard : false
		})
		
		
	});
	function addAnnoucement()
	{
		$("#nameE").val("");
		$("#addressE").val("");
		$("#mobileE").val("");
		$("#annouceE").val("");
		$("#annId").val("");
		$("#modaltitle").html("Add Announcement");
		
		$('#addAnnModal').modal('show');
	}
</script>