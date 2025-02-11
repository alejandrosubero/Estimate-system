import { EstimateListTabletPojo } from './estimate-list-tablet-pojo.model';
import { Work } from './work.model';
import { WorkListTabletPojo } from './workListTabletPojo.model';

export class DashboardData {

   numberEstimatesCreate: number;
   estimatesCreateInTheYear: number;
   estimatesCreateInTheMonth: number;
   numberEstimatesApproved: number;
   numberEstimatesSend: number;
   numberEstimatesInProgress: number;
   totalEstimateApprovedInYear: number;

   numberWorksCreate: number;
   worksCreateInTheYear: number;
   worksCreateInTheMonth: number;
   numberWorksStarted: number;
   numberWorksEnd: number;
   numberWorksInProgress: number;
   numberWorksApproved: number;
   numberWorksSend: number;
   numberWorksInPause: number;
   workFinalDateInMonth: number;
   workFinalDateInYear: number;

   numberWorksEndBeforeDedLine: number;
   numberWorksEndAfterDedLine: number;

   totalInvoicedInTheYear: number;
   totalAmountPaindIntYear: number;
   // totalRemainingPayableInTheYear: number;
   // totalRemainingPayableIntYear: number; 
   totalRemainingPayableInTheYear: number;
   worksEndBeforeDedLine: Array<Work>;
   worksBeforeDedLineAlert: Array<Work>;

   listPaidInvoiceEstimate: Array<number>;
   listEstimateApruveAndCreate: Array<number>;

   listAmountPaind: Array<WorkListTabletPojo>;
   listRemainingPayable: Array<WorkListTabletPojo>;
   listInvoiceInYear: Array<WorkListTabletPojo>;
   listWorksSend: Array<WorkListTabletPojo>;
   listEstimatesSend: Array<EstimateListTabletPojo>;
   listWorksFinalices: Array<WorkListTabletPojo>;
   listWorksInProgress: Array<WorkListTabletPojo>;
   listWorksPause: Array<WorkListTabletPojo>;


   constructor() {

      // this.numberEstimatesCreate =0;
      // this.estimatesCreateInTheYear =0;
      // this.estimatesCreateInTheMonth =0;
      // this.numberEstimatesApproved =0;
      // this.numberEstimatesSend =0;
      // this.numberEstimatesInProgress =0;
      // this.totalEstimateApprovedInYear =0;
      // this.numberWorksCreate =0;
      // this.worksCreateInTheYear =0;
      // this.worksCreateInTheMonth =0;
      // this.numberWorksStarted =0;
      // this.numberWorksEnd =0;
      // this.numberWorksInProgress =0;
      // this.numberWorksApproved =0;
      // this.numberWorksSend =0;
      // this.numberWorksInPause =0;
      // this.workFinalDateInMonth =0;
      // this.workFinalDateInYear =0;
      // this.numberWorksEndBeforeDedLine =0;
      // this.numberWorksEndAfterDedLine =0;
      // this.totalInvoicedInTheYear =0;
      // this.totalAmountPaindIntYear =0;
      // this.totalRemainingPayableInTheYear =0;

      this.worksEndBeforeDedLine = new Array<Work>();
      this.worksBeforeDedLineAlert = new Array<Work>();
      this.listPaidInvoiceEstimate = new Array<number>();
      this.listEstimateApruveAndCreate = new Array<number>();


      this.listAmountPaind = new Array<WorkListTabletPojo>();
      this.listRemainingPayable = new Array<WorkListTabletPojo>();
      this.listInvoiceInYear = new Array<WorkListTabletPojo>();
      this.listWorksSend = new Array<WorkListTabletPojo>();
      this.listEstimatesSend = new Array<EstimateListTabletPojo>();
      this.listWorksFinalices = new Array<WorkListTabletPojo>();

      this.listWorksInProgress = new Array<WorkListTabletPojo>();
      this.listWorksPause = new Array<WorkListTabletPojo>();
   }

   copyModel(value: DashboardData): DashboardData {
      let model: DashboardData = new DashboardData();
      model.numberEstimatesCreate = value.numberEstimatesCreate;
      model.estimatesCreateInTheYear = value.estimatesCreateInTheYear;
      model.estimatesCreateInTheMonth = value.estimatesCreateInTheMonth;
      model.numberEstimatesApproved = value.numberEstimatesApproved;
      model.numberEstimatesSend = value.numberEstimatesSend;
      model.numberEstimatesInProgress = value.numberEstimatesInProgress;
      model.totalEstimateApprovedInYear = value.totalEstimateApprovedInYear;
      model.numberWorksCreate = value.numberWorksCreate;
      model.worksCreateInTheYear = value.worksCreateInTheYear;
      model.worksCreateInTheMonth = value.worksCreateInTheMonth;
      model.numberWorksStarted = value.numberWorksStarted;
      model.numberWorksEnd = value.numberWorksEnd;
      model.numberWorksInProgress = value.numberWorksInProgress;
      model.numberWorksApproved = value.numberWorksApproved;
      model.numberWorksSend = value.numberWorksSend;
      model.numberWorksInPause = value.numberWorksInPause;
      model.workFinalDateInMonth = value.workFinalDateInMonth;
      model.workFinalDateInYear = value.workFinalDateInYear;
      model.numberWorksEndBeforeDedLine = value.numberWorksEndBeforeDedLine;
      model.numberWorksEndAfterDedLine = value.numberWorksEndAfterDedLine;
      model.totalInvoicedInTheYear = value.totalInvoicedInTheYear;
      model.totalAmountPaindIntYear = value.totalAmountPaindIntYear;
      model.totalRemainingPayableInTheYear = value.totalRemainingPayableInTheYear;
      model.worksEndBeforeDedLine = value.worksEndBeforeDedLine.length > 0 ? value.worksEndBeforeDedLine : new Array<Work>();
      model.worksBeforeDedLineAlert = value.worksBeforeDedLineAlert.length > 0 ? value.worksBeforeDedLineAlert : new Array<Work>();
      model.listPaidInvoiceEstimate = new Array<number>(value.totalAmountPaindIntYear, value.totalInvoicedInTheYear, value.totalEstimateApprovedInYear);
      model.listEstimateApruveAndCreate = new Array<number>(value.numberEstimatesCreate, value.numberEstimatesApproved);
      model.listAmountPaind = value.listAmountPaind;
      model.listRemainingPayable = value.listRemainingPayable;
      model.listInvoiceInYear = value.listInvoiceInYear;
      model.listEstimatesSend = value.listEstimatesSend;
      model.listWorksSend = value.listWorksSend;
      model.listWorksFinalices = value.listWorksFinalices;
      model.listWorksInProgress = value.listWorksInProgress;
      model.listWorksPause = value.listWorksPause;
      return model;
   }

}
