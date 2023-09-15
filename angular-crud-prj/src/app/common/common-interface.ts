declare global {
	interface Window { CKBox: any }
}

export interface CloudServicesConfig {
	webSocketUrl: string;
	tokenUrl: string;
	ckboxTokenUrl: string;
}
