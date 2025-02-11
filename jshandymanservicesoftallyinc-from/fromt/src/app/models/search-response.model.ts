
import { EstimateListTabletPojo } from './estimate-list-tablet-pojo.model';
import { Estimate } from './estimate.model';
import { ServiceHandyManTally } from './service-handy-man-tally.model';
import { Subcontractor } from './subcontractor.model';
import { Work } from './work.model';
import { WorkListTabletPojo } from './workListTabletPojo.model';

export class SearchResponse {

    works: Array<Work>;
    estimates: Array<Estimate>;
    workListTabletPojo: Array<WorkListTabletPojo>;
    estimateListTabletPojo: Array<EstimateListTabletPojo>;
    services: Array<ServiceHandyManTally>;
    subcontractors: Array<Subcontractor>;

    constructor() {
        this.works = new Array<Work>();
        this.estimates = new Array<Estimate>();
        this.workListTabletPojo = new  Array<WorkListTabletPojo>();
        this.estimateListTabletPojo = new  Array<EstimateListTabletPojo>();
        this.services = new  Array<ServiceHandyManTally>();
        this.subcontractors = new  Array<Subcontractor>();
    }
}

